package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.DataTablesPage;
import com.example.demo.common.DataTablesParam;
import com.example.demo.common.Result;
import com.example.demo.entity.SysUser;
import com.example.demo.service.UserService;
import com.example.demo.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
        return "user/list";
    }

    @RequestMapping(value = "/userListPage", method = RequestMethod.POST)
    @ResponseBody
    public DataTablesPage userListPage(DataTablesParam dataTablesParam, SysUser user, String searchStart) {
        if (StringUtils.isNotBlank(searchStart)) {
            user.setCreateTime(DateUtils.parseDate(searchStart));
        }
        return userService.userListPage(JSONObject.parseObject(dataTablesParam.getPageParam(),DataTablesParam.class), user);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, Model model) {
        SysUser sysUser = userService.findById(id);
        model.addAttribute("user", sysUser);
        return "user/edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(SysUser user) {
        return userService.save(user);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(SysUser user) {
        return userService.delete(user);
    }

}
