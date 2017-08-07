package com.bjqx.baseData.dao;

import com.bjqx.baseData.entity.RoleUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RoleUserMapper {

    /**
     * 列表查询
     *
     * @param params 查询参数
     * @return List<RoleUser>
     */
    List<RoleUser> findList(Map<String, Object> params);

    /**
     * 新增
     *
     * @param roleUser
     */
    void addOne(RoleUser roleUser);

    /**
     * 根据ID删除
     *
     * @param id
     */
    void delById(Integer id);

    /**
     * 更新
     *
     * @param roleUser
     */
    void updateOne(RoleUser roleUser);

    /**
     * 根据ID查询
     *
     * @param id
     * @return RoleUser
     */
    RoleUser findById(Integer id);
}
