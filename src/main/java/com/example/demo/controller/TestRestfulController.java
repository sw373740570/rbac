package com.example.demo.controller;

import com.example.demo.common.ResponseBean;
import com.example.demo.shiro.JWTToken;
import com.example.demo.utils.JWTUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestRestfulController {

//    @RequestMapping(path = "/401")
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @RequestMapping("/401")
    public ResponseBean unauthorized() {
        return new ResponseBean(401,"Unauthorized");
    }

    @PostMapping("/login")
    public ResponseBean login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        try {
            String token = JWTUtils.sign(username, password);
            subject.login(new JWTToken(token));
            return new ResponseBean(200,"登录成功", token);
        } catch (AuthenticationException e) {
            return new ResponseBean(200,e.getMessage());
        }
    }

    @GetMapping("/require_permission")
    public ResponseBean requirePermission() {
        return new ResponseBean(200, "You are visiting permission require edit,view");
    }
}
