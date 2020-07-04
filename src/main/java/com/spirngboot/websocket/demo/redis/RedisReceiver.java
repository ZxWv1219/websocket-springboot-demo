package com.spirngboot.websocket.demo.redis;

import com.alibaba.fastjson.JSONObject;
import com.spirngboot.websocket.demo.message.SendMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * 处理订阅redis的消息
 *
 * @author Zx
 * @date 2020/7/3 10:31
 * @modified By:
 */
@Component
public class RedisReceiver {
//    private final SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

//    public RedisReceiver(SimpMessagingTemplate simpMessagingTemplate) {
//        this.simpMessagingTemplate = simpMessagingTemplate;
//    }

    /**
     * 处理一对一消息
     *
     * @param message 消息队列中的消息
     */
    public void sendMsg(String message) {
        System.out.println(message);
        SendMsg msg = JSONObject.parseObject(message, SendMsg.class);
        simpMessagingTemplate.convertAndSendToUser(msg.getToUid(), "msg", msg);
    }

    /**
     * 处理广播消息
     *
     * @param message
     */
    public void sendAllMsg(String message) {
        System.out.println(message);
        simpMessagingTemplate.convertAndSend("/topic/sys", message);
    }
}