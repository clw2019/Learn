package com.clw.shiro;

import com.clw.domain.User;
import com.clw.service.PermissionService;
import com.clw.service.RoleService;
import com.clw.service.UserService;
import com.clw.util.JwtTokenUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/6/6 17:38
 */
public class MyRealm extends AuthorizingRealm {
    //@Resource
    //private JwtTokenUtil jwtTokenUtil;
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    PermissionService permissionService;

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String token = principalCollection.getPrimaryPrincipal().toString();
        System.out.println("MyRealm... " + token);
        // 从 token 中获取用户名
        String username = JwtTokenUtil.getUserNameFromToken(token);
        // 根据用户名查权限
        Set<String> roleSet = roleService.queryAllRolesByUsername(username);
        Set<String> permissionSet = permissionService.queryAllPermissionByUsername(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(roleSet);
        simpleAuthorizationInfo.setStringPermissions(permissionSet);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getPrincipal();
        String username = JwtTokenUtil.getUserNameFromToken(token);
        User user = userService.queryUserByUserName(username);
        System.out.println("....... " + user);
        if (user == null) {
            throw new AuthenticationException("用户不存在...");
        }
        // 判断是否被禁用，若被禁用抛出异常

        // 用户名校验
        if (!JwtTokenUtil.validateToken(token, user)) {
            throw new UnknownAccountException("用户名校验失败...");
        }
        return new SimpleAuthenticationInfo(token, token, "myRealm");
    }
}
