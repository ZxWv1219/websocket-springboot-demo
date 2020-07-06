package com.spirngboot.websocket.demo.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Zx
 * @date 2020/7/6 16:54
 * @modified By:
 */
@Component
public class JwtUtil {

    private final JwtConfig config;

    public JwtUtil(JwtConfig config) {
        this.config = config;
    }


    /**
     * 生成token
     *
     * @param userId
     * @return
     */
    public String createToken(String userId) {
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + config.getExpire() * 60 * 1000);
        //Map<String, Object> map = new HashMap<>();
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
//                .setClaims(map)//存放自定义数据
                .claim("zx", "存放自定义数据")//存放自定义数据
                .setSubject(userId)// 代表这个JWT的主体，即它的所有人
                .setIssuedAt(nowDate) //是一个时间戳，代表这个JWT的签发时间；
                .setExpiration(expireDate)//过期时间ms
                .signWith(SignatureAlgorithm.HS512, config.getSecret())
                .compact();
    }


    /**
     * 解析jwt
     *
     * @param jsonWebToken
     * @param base64Security
     * @return
     */
    public static Claims parseJWT(String jsonWebToken, String base64Security) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (ExpiredJwtException eje) {
            //TODO 抛出异常
            System.out.println("===== Token过期 =====");
            throw eje;
        } catch (Exception e) {
            //TODO 抛出异常
            System.out.println("===== token解析异常 =====");
            throw e;
        }
    }

    /**
     * 从token中获取用户名
     *
     * @param token
     * @return
     */
    public String getUserId(String token) {
        return parseJWT(token, config.getSecret()).getSubject();
    }

    /**
     * 是否已过期
     *
     * @param token
     * @return
     */
    public boolean isExpiration(String token) {
        return parseJWT(token, config.getSecret()).getExpiration().before(new Date());
    }

    public String getValue(String token, String key) {
        return parseJWT(token, config.getSecret()).get(key, String.class);
    }

}
