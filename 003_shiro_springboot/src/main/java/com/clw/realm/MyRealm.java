package com.clw.realm;

import com.clw.domain.User;
import com.clw.service.PermissionService;
import com.clw.service.RoleService;
import com.clw.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/6/4 17:56
 */
public class MyRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;

    /**
     * 作用：查权限信息,并返回即可，不用任何比对
     * 何时出发：/user/query = roles["admin"]    /user/insert = perms["user:insert"]     <shiro:hasRole  <shiro:hasPermission
     * 查询方式：通过用户名查询用户的 权限/角色 信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("在realm中 查询权限");
        // 获取当前用户的用户名
        String username = (String) principalCollection.getPrimaryPrincipal();
        // 查询当前用户的权限信息
        Set<String> roleSet = roleService.queryAllRolesByUsername(username);
        Set<String> permissionSet = permissionService.queryAllPermissionByUsername(username);
        // 将查询出的信息封装返回
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(roleSet);
        simpleAuthorizationInfo.setStringPermissions(permissionSet);
        return simpleAuthorizationInfo;
    }

    /**
     * 作用：查身份信息，并返回即可，不用任何比对
     * 查询方式：通过用户名查询用户信息
     * 何时触发：subject.login(token)
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("在Realm中查询身份");
        // 查询用户登录时发来的用户名
        String username = (String) authenticationToken.getPrincipal();
        // 查询用户信息
        User user = userService.queryUserByUserName(username);
        System.out.println("user... " + user);
        if (user == null) {
            return null;//在后续流程中跑出异常UnknownAccountException
        }
        // 将用户信息封装在 AuthenticationInfo 中
        return new SimpleAuthenticationInfo(user.getUsername(),
                                            user.getPassword(),
                                            ByteSource.Util.bytes(user.getSalt()),
                                            getName());//realm标识
    }
}
