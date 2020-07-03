package com.spirngboot.websocket.demo.message;

import javax.validation.constraints.NotNull;

/**
 * @author Zx
 * @date 2020/7/3 10:32
 * @modified By:
 */
public class SendMsg {
    //发送消息的用户id
    private String uid;

    //接收消息的用户id
    @NotNull(message = "未选择用户")
    private String toUid;

    //发送的文本消息
    @NotNull(message = "消息不能为空")
    private String content;

    public String getUid() {
        return uid;
    }

    public SendMsg setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public String getToUid() {
        return toUid;
    }

    public SendMsg setToUid(String toUid) {
        this.toUid = toUid;
        return this;
    }

    public String getContent() {
        return content;
    }

    public SendMsg setContent(String content) {
        this.content = content;
        return this;
    }
}
