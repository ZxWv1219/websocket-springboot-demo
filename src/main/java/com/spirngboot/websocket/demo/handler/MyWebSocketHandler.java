package com.spirngboot.websocket.demo.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;

/**
 * 用户登录退出操作
 * <p>
 * 记录用户登陆退出，将用户信息存储到redis中
 *
 * @author Zx
 * @date 2020/7/3 9:56
 * @modified By:
 */
@Component
public class MyWebSocketHandler implements WebSocketHandlerDecoratorFactory {
    public static final String ONLINE_KEY = "online";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public WebSocketHandler decorate(WebSocketHandler handler) {
        return new WebSocketHandlerDecorator(handler) {
            //用户登录
            @Override
            public void afterConnectionEstablished(WebSocketSession session) throws Exception {
                String uid = session.getPrincipal().getName();
                System.out.println(uid + "登录");
                //将用户存入到redis在线用户中
                stringRedisTemplate.opsForSet().add(ONLINE_KEY, uid);
                super.afterConnectionEstablished(session);
            }

            //用户退出
            @Override
            public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
                String uid = session.getPrincipal().getName();
                System.out.println(uid + "退出");
                //将用户从redis在线用户中删除
                stringRedisTemplate.opsForSet().remove(ONLINE_KEY, uid);
                super.afterConnectionClosed(session, closeStatus);
            }
        };
    }
}
