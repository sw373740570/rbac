package com.example.demo.shiro;

import com.example.demo.entity.SysResource;
import com.example.demo.entity.SysUser;
import com.example.demo.service.ResourceService;
import com.example.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private ResourceService resourceService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SysUser user = (SysUser)SecurityUtils.getSubject().getPrincipal();
        Map<String, Object> map = new HashMap<>();
        map.put("userId", user.getId());
        List<SysResource> resourceList = resourceService.getUserResources(map);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        for(SysResource resource : resourceList) {
            authorizationInfo.addStringPermission(resource.getPermission());
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        SysUser user = userService.findByUsername(username);
        if(user == null) {
            throw new UnknownAccountException();
        }
        if (user.getStatus() == -1) {
            throw new LockedAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user,user.getPassword(),ByteSource.Util.bytes(username),getName());
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("userSession", user);
        session.setAttribute("userSessionId", user.getId());
        return authenticationInfo;
    }
}
