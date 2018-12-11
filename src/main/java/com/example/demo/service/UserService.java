package com.example.demo.service;

import com.example.demo.common.DataTablesPage;
import com.example.demo.common.DataTablesParam;
import com.example.demo.common.Result;
import com.example.demo.dao.SysUserMapper;
import com.example.demo.entity.SysUser;
import com.example.demo.entity.SysUserExample;
import com.example.demo.utils.DateUtils;
import com.example.demo.utils.PasswordUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService extends BaseService{

    @Autowired
    private SysUserMapper userMapper;

    public SysUser findByUsername(String username) {
        SysUserExample userExample = new SysUserExample();
        userExample.createCriteria().andUsernameEqualTo(username).andIsDeletedEqualTo(0);
        List<SysUser> list= userMapper.selectByExample(userExample);
        return list == null || list.size()==0 ? null:list.get(0);
    }

    public DataTablesPage userListPage(DataTablesParam dataTablesParam, SysUser sysUser) {
        SysUserExample sysUserExample = new SysUserExample();
        SysUserExample.Criteria criteria = sysUserExample.createCriteria();
        criteria.andIsDeletedEqualTo(0);
        if (StringUtils.isNotBlank(sysUser.getName())) {
            criteria.andNameLike("%" + sysUser.getName() + "%");
        }
        if (StringUtils.isNotBlank(sysUser.getMobile())) {
            criteria.andMobileLike("%" + sysUser.getMobile() + "%");
        }
        if (sysUser.getCreateTime() != null) {
            criteria.andCreateTimeBetween(DateUtils.getStartTime(sysUser.getCreateTime()), DateUtils.getEndTime(sysUser.getCreateTime()));
        }
        PageInfo<SysUser> list = PageHelper.startPage(dataTablesParam.getPageNum(),dataTablesParam.getLength()).doSelectPageInfo(()->userMapper.selectByExample(sysUserExample));
        return new DataTablesPage(dataTablesParam.getDraw(),list);
    }

    public SysUser findById(Integer id) {
        SysUserExample userExample = new SysUserExample();
        userExample.createCriteria().andIsDeletedEqualTo(0).andIdEqualTo(id);
        return userMapper.selectOneByExample(userExample);
    }

    public Result save(SysUser user){
        if (StringUtils.isNotBlank(user.getPassword())) {
            user.setPassword(PasswordUtils.encryptPassword(user.getPassword(), user.getUsername()));
        }
        SysUser loginUser = (SysUser)SecurityUtils.getSubject().getPrincipal();
        user.setUpdateBy(loginUser.getId().toString());
        user.setUpdateTime(new Date());
        if(user.getId() == null) {
            if(null != findByUsername(user.getUsername())) {
                return new Result(false,"用户名已经存在");
            }
            user.setStatus(0);
            user.setIsDeleted(0);
            user.setCreateBy(loginUser.getId().toString());
            user.setCreateTime(new Date());
            userMapper.insertSelective(user);
        } else {
            userMapper.updateByPrimaryKeySelective(user);
        }
        return new Result(true,"保存成功");
    }

    public Result delete(SysUser user) {
        user.setIsDeleted(1);
        userMapper.updateByPrimaryKeySelective(user);
        return new Result(true,"删除成功");
    }
}
