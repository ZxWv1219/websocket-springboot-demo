package com.spirngboot.websocket.demo.jwt;

/**
 * @author ：Zx
 * @date ：2020/7/6 17:00
 * @modified By：
 */
public interface IUserService {

    /**
     * 校验用户信息
     *
     * @param loginName
     * @param passWord
     * @return
     */
    boolean checkUser(String loginName, String passWord);

    /**
     * 查询用户信息
     *
     * @param loginName
     * @return
     */
    User getUser(String loginName);

}
