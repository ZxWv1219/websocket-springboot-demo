package com.spirngboot.websocket.demo.jwt;


import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * @author Zx
 * @date 2020/7/6 16:59
 * @modified By:
 */
@RestController
@RequestMapping
public class LoginController {
    private final IUserService userService;
    private final JwtUtil jwtUtil;
    private final JwtConfig config;

    public LoginController(IUserService userService, JwtUtil jwtUtil, JwtConfig config) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.config = config;
    }

    @PostMapping("/login")
    public ResponseResult login(@RequestBody Map<String, String> map) {
        String loginName = map.get("loginName");
        String passWord = map.get("passWord");
        //身份验证
        boolean isSuccess = userService.checkUser(loginName, passWord);
        if (isSuccess) {
            //模拟数据库查询
            User user = userService.getUser(loginName);
            if (user != null) {
                //返回token
                String token = jwtUtil.createToken(loginName);
                if (token != null) {
                    return ResponseResult.success("成功", token);
                }
            }
        }
        return ResponseResult.fail();
    }

    @PostMapping("getUser")
    public String getUserInfo(HttpServletRequest request, @RequestBody Map<String, String> map) {
        String token = request.getHeader(config.getHeader());
//        return ResponseResult.success(jwtUtil.getUserId(token));
        return jwtUtil.getValue(token, "zx");
    }
}
