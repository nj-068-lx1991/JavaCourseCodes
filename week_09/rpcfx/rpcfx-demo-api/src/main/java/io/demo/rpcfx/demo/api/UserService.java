package io.demo.rpcfx.demo.api;

/**
 * @author Created by lx_068
 * @date 2021/12/30
 */
public interface UserService {

    /**
     * 根据id查询用户信息
     *
     * @param id 主键
     * @return io.demo.rpcfx.demo.api.User
     */
    User findById(int id);
}
