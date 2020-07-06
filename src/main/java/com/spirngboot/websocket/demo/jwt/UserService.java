package com.spirngboot.websocket.demo.jwt;

import org.springframework.stereotype.Service;

/**
 * @author Zx
 * @date 2020/7/6 17:02
 * @modified By:
 */
@Service
public class UserService implements IUserService {
    @Override
    public boolean checkUser(String loginName, String passWord) {
        return true;
    }

    @Override
    public User getUser(String loginName) {
        User user = new User();
        user.setName("李四");
        user.setPassword("123");
        return user;
    }
}
