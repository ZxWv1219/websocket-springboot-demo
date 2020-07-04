package com.spirngboot.websocket.demo.handler;

import com.spirngboot.websocket.demo.principal.MyPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.security.Principal;
import java.util.Map;

/**
 * @author Zx
 * @date 2020/7/3 19:01
 * @modified By:
 */
//@Component
public class MyHandshakeInterceptor implements HandshakeInterceptor {

    // feign实例调用用户中心获取Principal或税号等信息对象
    @Autowired
//    private PrinalInterface prinalInterface;

    /**
     * websocket握手连接
     *
     * @return 返回是否同意握手
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        ServletServerHttpRequest req = (ServletServerHttpRequest) request;
        //通过url的query参数获取认证参数
        String token = req.getServletRequest().getParameter("token");
        //根据token认证用户并拿到用户信息，不通过返回拒绝握手
        Principal user = authenticate();
        if (user == null) {
//            logger.info("Authentication is failed!!! Connection rejection.");
            System.out.println("认证失败");
            return false;
        }
//        logger.info("Authentication is Ok,Saving Authenticated Users,Username is " + user.getName());
        //保存认证用户
        attributes.put("token", token);
        attributes.put("user", user);
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }

    /**
     * 根据token认证授权
     */
    private Principal authenticate() {
        //TODO 实现用户的认证并返回用户信息，如果认证失败返回 null
        // 一种：用户信息需继承 Principal 并实现 getName() 方法，返回全局唯一值
        // 二种：这里实现的是用token换取用户信息
//        String username = prinalInterface.member();
//        MyPrincipal principal = prinalInterface.test();
//        if (principal == null) {
////                logger.error("Failed to invoke authentication service！");
//            return null;
//        }
//        principal.setUsername("12345678900" + username);
//        System.out.println(principal.toString());
//        System.out.println(principal.getName());
////        logger.info("FeignClient is succeed,Username is:" + username);
//        if (principal != null)
//            return principal;
        return null;
    }

}
