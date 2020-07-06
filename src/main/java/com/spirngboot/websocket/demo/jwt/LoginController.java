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

    public LoginController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("login")
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
                String token = JwtUtil.sign(loginName, passWord);
                if (token != null) {
                    return ResponseResult.success("成功", token);
                }
            }
        }
        return ResponseResult.fail();
    }

    @PostMapping("getUser")
    public ResponseResult getUserInfo(HttpServletRequest request, @RequestBody Map<String, String> map) {
        String loginName = map.get("loginName");
        String token = request.getHeader("token");
        boolean verity = JwtUtil.verity(token);
        if (verity) {
            User user = userService.getUser(loginName);
            if (user != null) {
                return ResponseResult.success("成功", JSONObject.toJSONString(user));
            }
        }
        return ResponseResult.fail();
    }

}
