package com.clw.shiro;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.clw.sys.entity.Permission;
import com.clw.sys.entity.Role;
import com.clw.sys.entity.User;
import com.clw.sys.service.impl.UserService;
import com.clw.sys.vo.LoginUser;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 自定义realm
 */
public class CustomRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        System.out.println("========    Shiro调用权限认证   ========");

        // 获取身份信息
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        //根据主身份信息获取角色 和 权限信息
        LoginUser loginUser = userService.findRolesByUsername(primaryPrincipal);

        if (CollectionUtils.isNotEmpty(loginUser.getRoles())){
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            loginUser.getRoles().forEach(role -> {
                // 角色信息
                simpleAuthorizationInfo.addRole(role.getName());

                // 权限信息
                List<Permission> permissions = userService.findPermissionByRoleId(role.getId());
                if (CollectionUtils.isNotEmpty(permissions)) {
                    permissions.forEach(permission -> {
                        simpleAuthorizationInfo.addStringPermission(permission.getName());
                    });
                }


            });
            return simpleAuthorizationInfo;
        }

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("========    Shiro认证开始   ========");
        String principal = (String) authenticationToken.getPrincipal();

        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, principal));
        if (!ObjectUtils.isEmpty(user)) {
            return new SimpleAuthenticationInfo(principal, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
        }

        return null;
    }
}
