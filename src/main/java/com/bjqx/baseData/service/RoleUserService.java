package com.bjqx.baseData.service;

import com.bjqx.base.common.GlobalConfigure;
import com.bjqx.baseData.dao.RoleUserMapper;
import com.bjqx.baseData.entity.RoleUser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class RoleUserService {

    @Resource
    private RoleUserMapper roleUserMapper;

    /**
     * 分页查询
     *
     * @param roleUser
     * @param pageNo   页码
     */
    public PageInfo<RoleUser> queryByPage(RoleUser roleUser, Integer pageNo) {
        PageHelper.startPage(pageNo, GlobalConfigure.DEFAULT_PAGE_SIZE);
        Map<String, Object> parameter = new HashMap<String, Object>();
        // TODO 查询参数
        List<RoleUser> list = roleUserMapper.findList(parameter);
        PageInfo<RoleUser> page = new PageInfo<RoleUser>(list);
        return page;
    }

    /**
     * 新增
     *
     * @param roleUser
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void addOne(RoleUser roleUser) {
        if (roleUser == null) {
            throw new RuntimeException("要保存的对象为空,操作失败.");
        }
        this.roleUserMapper.addOne(roleUser);
    }

    /**
     * 修改
     *
     * @param roleUser
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void updateOne(RoleUser roleUser) {
        if (roleUser == null) {
            throw new RuntimeException("要修改的对象为空,操作失败.");
        }
        if (roleUser.getId() == null) {
            throw new RuntimeException("要修改的对象ID为空,操作失败.");
        }
        this.roleUserMapper.updateOne(roleUser);
    }

    /**
     * 根据ID删除
     *
     * @param id
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void delById(Integer id) {
        if (null == id) {
            throw new RuntimeException("缺少参数ID,操作失败.");
        }
        this.roleUserMapper.delById(id);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return RoleUser
     */
    public RoleUser findById(Integer id) {
        if (null == id) {
            throw new RuntimeException("缺少参数ID,操作失败.");
        }
        RoleUser roleUser = this.roleUserMapper.findById(id);
        return roleUser;
    }
}
