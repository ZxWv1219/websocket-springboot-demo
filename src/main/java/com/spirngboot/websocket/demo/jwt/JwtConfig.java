package com.spirngboot.websocket.demo.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Zx
 * @date 2020/7/6 20:36
 * @modified By:
 */

@ConfigurationProperties(prefix = "config.jwt")
@Component
public class JwtConfig {
    private String secret;
    private long expire;
    private String header;

    public String getSecret() {
        return secret;
    }

    public JwtConfig setSecret(String secret) {
        this.secret = secret;
        return this;
    }

    public long getExpire() {
        return expire;
    }

    public JwtConfig setExpire(long expire) {
        this.expire = expire;
        return this;
    }

    public String getHeader() {
        return header;
    }

    public JwtConfig setHeader(String header) {
        this.header = header;
        return this;
    }
}
