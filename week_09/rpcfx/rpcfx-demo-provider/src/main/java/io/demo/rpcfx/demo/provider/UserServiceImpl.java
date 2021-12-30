package io.demo.rpcfx.demo.provider;

import io.demo.rpcfx.demo.api.User;
import io.demo.rpcfx.demo.api.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Created by lx_068
 * @date 2021/12/30
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public User findById(int id) {
        return new User(id, "KK" + System.currentTimeMillis());
    }
}
