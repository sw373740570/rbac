package com.example.demo.shiro;

import com.example.demo.entity.SysResource;
import com.example.demo.entity.SysUser;
import com.example.demo.service.ResourceService;
import com.example.demo.service.UserService;
import com.example.demo.utils.JWTUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
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
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

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
        String token = (String) authenticationToken.getPrincipal();
        if (StringUtils.isEmpty(token)){
            throw new AuthenticationException("token invalid");
        }
        String username = JWTUtils.getUsername(token);
        if (StringUtils.isEmpty(username)){
            throw new AuthenticationException("token invalid");
        }
        SysUser user = userService.findByUsername(JWTUtils.getUsername(token));
        if(user == null) {
            throw new UnknownAccountException();
        }
        if (user.getStatus() == -1) {
            throw new LockedAccountException();
        }
        if (! JWTUtils.verify(token, username, user.getPassword())) {
            throw new AuthenticationException("Username or password error");
        }
        return new SimpleAuthenticationInfo(user, token, getName());
    }
}
