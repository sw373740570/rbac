package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.DataTablesPage;
import com.example.demo.common.DataTablesParam;
import com.example.demo.common.Result;
import com.example.demo.entity.SysUser;
import com.example.demo.service.UserService;
import com.example.demo.shiro.ShiroService;
import com.example.demo.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Map;

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
    @ResponseBody
    public DataTablesPage userListPage(DataTablesParam dataTablesParam, SysUser user, String searchStart) {
        if (StringUtils.isNotBlank(searchStart)) {
            user.setCreateTime(DateUtils.parseDate(searchStart));
        }
        return userService.userListPage(JSONObject.parseObject(dataTablesParam.getPageParam(),DataTablesParam.class), user);
    }

}
