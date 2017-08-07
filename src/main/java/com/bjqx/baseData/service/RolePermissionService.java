package com.bjqx.baseData.service;

import com.bjqx.base.common.GlobalConfigure;
import com.bjqx.baseData.dao.RolePermissionMapper;
import com.bjqx.baseData.entity.RolePermission;
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
public class RolePermissionService {

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    /**
     * 分页查询
     *
     * @param rolePermission
     * @param pageNo         页码
     */
    public PageInfo<RolePermission> queryByPage(RolePermission rolePermission, Integer pageNo) {
        PageHelper.startPage(pageNo, GlobalConfigure.DEFAULT_PAGE_SIZE);
        Map<String, Object> parameter = new HashMap<String, Object>();
        // TODO 查询参数
        List<RolePermission> list = rolePermissionMapper.findList(parameter);
        PageInfo<RolePermission> page = new PageInfo<RolePermission>(list);
        return page;
    }

    /**
     * 新增
     *
     * @param rolePermission
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void addOne(RolePermission rolePermission) {
        if (rolePermission == null) {
            throw new RuntimeException("要保存的对象为空,操作失败.");
        }
        this.rolePermissionMapper.addOne(rolePermission);
    }

    /**
     * 修改
     *
     * @param rolePermission
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void updateOne(RolePermission rolePermission) {
        if (rolePermission == null) {
            throw new RuntimeException("要修改的对象为空,操作失败.");
        }
        if (rolePermission.getId() == null) {
            throw new RuntimeException("要修改的对象ID为空,操作失败.");
        }
        this.rolePermissionMapper.updateOne(rolePermission);
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
        this.rolePermissionMapper.delById(id);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return RolePermission
     */
    public RolePermission findById(Integer id) {
        if (null == id) {
            throw new RuntimeException("缺少参数ID,操作失败.");
        }
        RolePermission rolePermission = this.rolePermissionMapper.findById(id);
        return rolePermission;
    }
}
