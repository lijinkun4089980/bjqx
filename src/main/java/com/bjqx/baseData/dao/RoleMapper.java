package com.bjqx.baseData.dao;

import com.bjqx.baseData.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RoleMapper {

    /**
     * 列表查询
     *
     * @param params 查询参数
     * @return List<Role>
     */
    List<Role> findList(Map<String, Object> params);

    /**
     * 新增
     *
     * @param role
     */
    void addOne(Role role);

    /**
     * 根据ID删除
     *
     * @param id
     */
    void delById(Integer id);

    /**
     * 更新
     *
     * @param role
     */
    void updateOne(Role role);

    /**
     * 根据ID查询
     *
     * @param id
     * @return Role
     */
    Role findById(Integer id);
}
