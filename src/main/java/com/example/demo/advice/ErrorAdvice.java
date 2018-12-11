package com.example.demo.advice;

import com.example.demo.common.ResponseBean;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ErrorAdvice {

    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseBean handleAuthenticationException(HttpServletRequest req, AuthenticationException e) {
        return new ResponseBean(401,e.getMessage());
    }

    //@ExceptionHandler(value = Exception.class)
    public String error500(HttpServletRequest req, Exception e) {
        e.printStackTrace();
        return "/500";
    }
}
