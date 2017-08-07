package com.bjqx.baseData.dao;

import com.bjqx.baseData.entity.RolePermission;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RolePermissionMapper {

    /**
     * 列表查询
     *
     * @param params 查询参数
     * @return List<RolePermission>
     */
    List<RolePermission> findList(Map<String, Object> params);

    /**
     * 新增
     *
     * @param rolePermission
     */
    void addOne(RolePermission rolePermission);

    /**
     * 根据ID删除
     *
     * @param id
     */
    void delById(Integer id);

    /**
     * 更新
     *
     * @param rolePermission
     */
    void updateOne(RolePermission rolePermission);

    /**
     * 根据ID查询
     *
     * @param id
     * @return RolePermission
     */
    RolePermission findById(Integer id);
}
