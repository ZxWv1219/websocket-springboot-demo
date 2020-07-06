package com.spirngboot.websocket.demo.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 配置Token拦截器
 *
 * @author Zx
 * @date 2020/7/6 21:03
 * @modified By:
 */
@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {
    @Resource
    private JwtConfig jwtConfig;
    @Resource
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws SignatureException {
        /** 地址过滤 */
        String uri = request.getRequestURI();
        if (uri.contains("/login")) {
            return true;
        }
        /** Token 验证 */
        String token = request.getHeader(jwtConfig.getHeader());
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter(jwtConfig.getHeader());
        }
        if (StringUtils.isEmpty(token)) {
            //TODO 非demo环境请统封装异常
            throw new SignatureException(jwtConfig.getHeader() + "不能为空");
        }

        try {
            if (jwtUtil.isExpiration(token)) {
                //TODO 非demo环境请统封装异常
                throw new SignatureException(jwtConfig.getHeader() + "token失效，请重新登录。");
            }
        } catch (Exception e) {
            //TODO 非demo环境请统封装异常
            throw new SignatureException(jwtConfig.getHeader() + "token失效，请重新登录。");
        }

        /** 设置 identityId 用户身份ID */
//        request.setAttribute("identityId", claims.getSubject());
        return true;
    }


}
