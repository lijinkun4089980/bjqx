package com.bjqx.base.common;

import com.bjqx.base.utils.BjqxUtils;
import com.bjqx.base.utils.JsonUtils;
import com.bjqx.base.utils.SessionUtil;
import com.bjqx.baseData.entity.User;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

public class BaseRealm extends AuthorizingRealm {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    //@Autowired
    //private ProjectService projectService;
    //@Autowired
    //private DepartmentService departmentService;// 部门机构
    //@Autowired
    //private RailLineService railLineService;// 部门机构
    //@Autowired
    //private BlockService blockService;// 部门机构
    //@Autowired
    //private TrackService trackService;// 部门机构
    //@Autowired
    //private DictionaryItemService dictionaryItemService;

    private static final Logger logger = LoggerFactory.getLogger(BaseRealm.class);

    /*
     * (non-Javadoc)
     *
     * @see
     * org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.
     * apache.shiro.authc.AuthenticationToken)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        AuthenticationInfo authenticationInfo = super.getAuthenticationInfo(authcToken);

        // 获取登录账号
        PrincipalCollection principalCollection = authenticationInfo.getPrincipals();

        String loginName = (String) principalCollection.getPrimaryPrincipal();
        Session session = SessionUtil.getSession();

        if (StringUtils.isEmpty(loginName)) {
            throw new AccountException("Null usernames are not allowed by this realm.");
        }
        HashOperations<String, String, Object> hashOperations = this.redisTemplate.opsForHash();
        String key = "BJQX:User:{" + loginName + "}";

        String str = (String) hashOperations.get(key, "currentUser");
        User currentUser = null;
        if (StringUtils.isNotBlank(str)) {
            try {
                currentUser = JsonUtils.readJson(str, User.class);
            } catch (Exception e) {

            }
        }
        if (null == currentUser) {
            throw new UnknownAccountException();
        }

        UserContext userContext = new UserContext();
        userContext.setCurrentUser(currentUser);
        ThreadLocalWrapper.bind(userContext);

        session.setAttribute(ThreadLocalWrapper.USER_CONTEXT_KEY, userContext);
        session.setAttribute(GlobalConfigure.EMPLOYEE, userContext.getCurrentUser());
        if (userContext.getCurrentUser().getId() != null) {
            //List<Department> departmentList = departmentService.findByEmployeeId(userContext.getCurrentUser().getId());
            //if (departmentList != null && !departmentList.isEmpty()) {
            //    session.setAttribute(GlobalConfigure.DEPARTMENT_LIST, departmentList);
            //    // 查询当前人所属段，如果是段级以上的部门，当前字段就为空
            //    for (Department department : departmentList) {
            //        if (department.getType() != null) {
            //            DictionaryItem dicItem = this.dictionaryItemService.findById(department.getType());
            //            if (dicItem == null) {
            //                continue;
            //            }
            //            if (DepartmentCons.TYPE_SECTION.equals(dicItem.getDictionaryCode())) {
            //                session.setAttribute(GlobalConfigure.TYPE_SECTION, department);
            //                break;
            //            } else {
            //                if (null != department.getPid()) {
            //
            //                    Department obj = departmentService.findById(department.getPid());
            //                    if (null != obj) {
            //                        DictionaryItem pItem = this.dictionaryItemService.findById(obj.getType());
            //                        if (pItem == null) {
            //                            continue;
            //                        }
            //                        if (DepartmentCons.TYPE_SECTION.equals(pItem.getDictionaryCode())) {
            //                            session.setAttribute(GlobalConfigure.TYPE_SECTION, obj);
            //                            break;
            //                        } else if (DepartmentCons.TYPE_WORKSHOP.equals(pItem.getDictionaryCode())) {
            //                            session.setAttribute(GlobalConfigure.TYPE_SECTION, departmentService.findById(obj.getPid()));
            //                            break;
            //                        }
            //                    }
            //                }
            //            }
            //        }
            //    }
            //    List<Integer> deptIdList = new ArrayList<Integer>();
            //    for (Department dept : departmentList) {
            //        deptIdList.add(dept.getId());
            //    }
            //}
        }
        return authenticationInfo;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache
     * .shiro.subject.PrincipalCollection)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User currentUser = BjqxUtils.getUserContext();

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        HashOperations<String, String, Object> hashOperations = this.redisTemplate.opsForHash();
        String key = "ZT:Employee:{" + currentUser.getLoginName() + "}";

        String str = (String) hashOperations.get(key, "permissionCodes");
        List<String> perStrs = null;
        if (StringUtils.isNotBlank(str)) {
            try {
                perStrs = JsonUtils.readJson(str, List.class, String.class);
            } catch (Exception e) {
            }
        }
        info.addStringPermissions(perStrs);
        return info;
    }

}
