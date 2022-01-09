package com.rpc.demo.service;

import com.rpc.demo.model.User;

/**
 * @author Created by lx_068
 * @date 2022/01/09
 */
public interface UserService {

    /**
     * 根据id查询用户信息
     * @param id id
     * @return user
     */
    User findById(Integer id);
}
