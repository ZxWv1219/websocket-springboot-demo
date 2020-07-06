package com.spirngboot.websocket.demo.jwt;

/**
 * @author Zx
 * @date 2020/7/6 16:56
 * @modified By:
 */
public class User {
    /**
     * 昵称
     */
    private String name;
    /**
     * 密码
     */
    private String password;

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }
}
