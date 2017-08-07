package com.bjqx.baseData.service;

import com.bjqx.base.common.GlobalConfigure;
import com.bjqx.baseData.dao.RoleMapper;
import com.bjqx.baseData.entity.Role;
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
public class RoleService {

    @Resource
    private RoleMapper roleMapper;

    /**
     * 分页查询
     *
     * @param role
     * @param pageNo 页码
     */
    public PageInfo<Role> queryByPage(Role role, Integer pageNo) {
        PageHelper.startPage(pageNo, GlobalConfigure.DEFAULT_PAGE_SIZE);
        Map<String, Object> parameter = new HashMap<String, Object>();
        // TODO 查询参数
        List<Role> list = roleMapper.findList(parameter);
        PageInfo<Role> page = new PageInfo<Role>(list);
        return page;
    }

    /**
     * 新增
     *
     * @param role
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void addOne(Role role) {
        if (role == null) {
            throw new RuntimeException("要保存的对象为空,操作失败.");
        }
        this.roleMapper.addOne(role);
    }

    /**
     * 修改
     *
     * @param role
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void updateOne(Role role) {
        if (role == null) {
            throw new RuntimeException("要修改的对象为空,操作失败.");
        }
        if (role.getId() == null) {
            throw new RuntimeException("要修改的对象ID为空,操作失败.");
        }
        this.roleMapper.updateOne(role);
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
        this.roleMapper.delById(id);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return Role
     */
    public Role findById(Integer id) {
        if (null == id) {
            throw new RuntimeException("缺少参数ID,操作失败.");
        }
        Role role = this.roleMapper.findById(id);
        return role;
    }
}
