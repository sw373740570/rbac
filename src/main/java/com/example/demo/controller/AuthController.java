package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.vo.LoginVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthController {

    @RequestMapping(value = {"/login","/"})
    public String login() {
        return "login";
    }

    @RequestMapping(value = {"/index"})
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    @ResponseBody
    public Result doLogin(LoginVO vo) {
        if(StringUtils.isBlank(vo.getUsername()) || StringUtils.isBlank(vo.getPassword())) {
            return new Result(false,"用户名或密码不能为空");
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(vo.getUsername(), vo.getPassword());
        try {
            subject.login(token);
            return new Result(true, "登录成功");
        } catch (LockedAccountException e) {
            token.clear();
            return new Result(false, "账户被锁定，请联系管理员");
        } catch (AuthenticationException e) {
            token.clear();
            return new Result(false, "用户名或密码错误");
        } catch (Exception e) {
            token.clear();
            return new Result(false, "登录失败");
        }
    }
}
