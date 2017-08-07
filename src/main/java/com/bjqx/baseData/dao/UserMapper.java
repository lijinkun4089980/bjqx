package com.bjqx.baseData.dao;

import com.bjqx.baseData.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {

    /**
     * 列表查询
     *
     * @param params 查询参数
     * @return List<User>
     */
    List<User> findList(Map<String, Object> params);

    /**
     * 新增
     *
     * @param user
     */
    void addOne(User user);

    /**
     * 根据ID删除
     *
     * @param id
     */
    void delById(Integer id);

    /**
     * 更新
     *
     * @param user
     */
    void updateOne(User user);

    /**
     * 根据ID查询
     *
     * @param id
     * @return User
     */
    User findById(Integer id);
}
