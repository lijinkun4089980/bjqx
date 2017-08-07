package com.bjqx.baseData.intercepter;


import com.bjqx.base.common.GlobalConfigure;
import com.bjqx.base.utils.BjqxUtils;
import com.bjqx.base.utils.JsonUtils;
import com.bjqx.baseData.entity.Permission;
import com.bjqx.baseData.entity.User;
import com.bjqx.baseData.service.PermissionService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * 权限拦截器
 *
 * @author 阿坤
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUrl = request.getServletPath();
        HashOperations<String, String, Object> hashOperations = this.redisTemplate.opsForHash();
        Boolean noInterceptor = (Boolean) request.getAttribute("noInterceptor");
        if (noInterceptor != null && noInterceptor.booleanValue()) {
            return true;
        }
        User currentUser = BjqxUtils.getUserContext();
        StringBuilder builder = new StringBuilder();
        builder.append(GlobalConfigure.URI_DEFINITION_KEY).append(":").append(requestUrl);
        String key = "BJQX:User:{" + currentUser.getLoginName() + "}";
        String userUriAccessCode = (String) hashOperations.get(key, GlobalConfigure._USER_URI_DEFINITION_KEY);
        String permissionCode = null;
        Map<String, String> userUriAccessCodeMap = null;
        if (StringUtils.isNotBlank(userUriAccessCode)) {
            userUriAccessCodeMap = JsonUtils.stringToObj(userUriAccessCode, Map.class);
            if (userUriAccessCodeMap != null) {
                permissionCode = userUriAccessCodeMap.get(builder.toString());
                if (StringUtils.isNotBlank(permissionCode) && permissionCode.equals("uriNotExistInDB")) {
                    return true;
                }
            }
        } else {
            userUriAccessCodeMap = new HashMap<String, String>();
        }

        if (StringUtils.isBlank(permissionCode)) {
            Permission permission = this.permissionService.findByUri(requestUrl);
            //如果没有找到相应的权限,则默认可以访问.
            if (permission == null) {
                userUriAccessCodeMap.put(builder.toString(), "uriNotExistInDB");
                hashOperations.put(key, GlobalConfigure._USER_URI_DEFINITION_KEY, JsonUtils.objToString(userUriAccessCodeMap));
                return true;
            }
            permissionCode = "666";
            //permissionCode = permission.getCode();
            userUriAccessCodeMap.put(builder.toString(), permissionCode);
            hashOperations.put(key, GlobalConfigure._USER_URI_DEFINITION_KEY, JsonUtils.objToString(userUriAccessCodeMap));
        }

        Subject subject = SecurityUtils.getSubject();
        if (!subject.isPermitted(permissionCode)) {
            request.getRequestDispatcher("/WEB-INF/view/error/403.jsp").forward(request, response);
            return false;
        }
        return super.preHandle(request, response, handler);
    }
}
