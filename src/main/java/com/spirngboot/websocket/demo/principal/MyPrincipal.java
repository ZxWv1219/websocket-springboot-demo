package com.spirngboot.websocket.demo.principal;

import java.security.Principal;

/**
 * @author Zx
 * @date 2020/7/3 9:54
 * @modified By:
 */
public class MyPrincipal implements Principal {
    private String name;

    public MyPrincipal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
