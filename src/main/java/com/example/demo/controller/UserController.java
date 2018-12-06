package com.example.demo.controller;

import com.example.demo.common.DataTablesPage;
import com.example.demo.common.DataTablesParam;
import com.example.demo.common.Result;
import com.example.demo.entity.SysUser;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @RequestMapping(value = "helloWorld")
    public String helloWorld() {
        return "index";
    }

    @RequestMapping("/list")
    public String userList() {
        logger.info("testLog");
        return "user/userList";
    }

    @RequestMapping(value = "/userListPage", method = RequestMethod.POST)
    public DataTablesPage userListPage(DataTablesParam dataTablesParam, SysUser sysUser) {
        return userService.userListPage(dataTablesParam, sysUser);
    }

}
