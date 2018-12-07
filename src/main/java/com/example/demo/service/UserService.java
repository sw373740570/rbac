package com.example.demo.service;

import com.example.demo.common.DataTablesPage;
import com.example.demo.common.DataTablesParam;
import com.example.demo.dao.SysUserMapper;
import com.example.demo.entity.SysUser;
import com.example.demo.entity.SysUserExample;
import com.example.demo.utils.DateUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends BaseService{

    @Autowired
    private SysUserMapper userMapper;

    public SysUser findByUsername(String username) {
        SysUserExample userExample = new SysUserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        List<SysUser> list= userMapper.selectByExample(userExample);
        return list == null || list.size()==0 ? null:list.get(0);
    }

    public DataTablesPage userListPage(DataTablesParam dataTablesParam, SysUser sysUser) {
        SysUserExample sysUserExample = new SysUserExample();
        SysUserExample.Criteria criteria = sysUserExample.createCriteria();
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
}
