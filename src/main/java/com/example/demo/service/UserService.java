package com.example.demo.service;

import com.example.demo.dao.SysUserMapper;
import com.example.demo.entity.SysUser;
import com.example.demo.entity.SysUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private SysUserMapper userMapper;

    public SysUser findByUsername(String username) {
        SysUserExample userExample = new SysUserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        List<SysUser> list= userMapper.selectByExample(userExample);
        return list == null || list.size()==0 ? null:list.get(0);
    }
}
