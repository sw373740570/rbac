package com.example.demo.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorAdvice {

    @ExceptionHandler(value = Exception.class)
    public String error500(HttpServletRequest req, Exception e) {
        e.printStackTrace();
        return "/500";
    }
}
