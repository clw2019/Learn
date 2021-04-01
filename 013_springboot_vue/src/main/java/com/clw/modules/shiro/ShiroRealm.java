package com.clw.modules.shiro;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.clw.modules.sys.entity.User;
import com.clw.modules.sys.service.UserService;
import com.clw.modules.sys.vo.LoginUser;
import com.clw.modules.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @Author: clw
 * @Description: 自定义ShiroRealm
 * @Date: 2021/3/8 22:00
 */
@Component
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    /**
    * @Author: clw
    * @Description: 必须重写此方法，不然Shiro会报错
     * ps: 每一个Realm都有一个 supports 用于检测是否支持此Token，默认是 return false 的
    * @Param: [token]
    * @return: boolean
    * @Date: 2021/3/14 14:03
    */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Resource
    private UserService userService;
    
    /**
    * @Author: clw
    * @Description: 权限信息认证。用户访问Controller的时候才进行验证（Redis存储此处的权限信息）
    * @Param: [principalCollection] [身份信息]
    * @return: org.apache.shiro.authz.AuthorizationInfo权限信息
    * @Date: 2021/3/8 22:12
    */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("=====================[Shiro权限认证开始]=====================");
        String username = null;
        if (principalCollection != null) {
            LoginUser loginUser = (LoginUser) principalCollection.getPrimaryPrincipal();
            username = loginUser.getUsername();
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 设置用户拥有的角色集合，比如“admin,test”
        Set<String> roleSet = userService.queryRoleSetByUsername(username);
        info.setStringPermissions(roleSet);

        // 设置用户拥有的权限集合，比如“sys:role:add,sys:user:add”
        Set<String> permissionSet = userService.queryPermissionByUsername(username);
        info.setStringPermissions(permissionSet);
        // info.addStringPermissions(roleSet);  TODO jeecg-boot 使用的
        log.info("=====================[Shiro权限认证结束]=====================");
        return info;
    }

    /**
    * @Author: clw
    * @Description: 用户登录时进行身份认证（不存Redis ）
    * @Param: [authenticationToken] [token]
    * @return: org.apache.shiro.authc.AuthenticationInfo 返回封装用户信息的 AuthenticationInfo 实例
    * @Date: 2021/3/8 22:14
    */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("=====================[Shiro身份认证开始]=====================");
        String token = (String) authenticationToken.getCredentials();
        if(token == null) {
            log.info("=====================[Shiro身份认证失败：token为空]=====================");
            throw new AuthenticationException("token为空！");
        }
        LoginUser loginUser = checkUserTokenIsEffect(token);
        log.info("=====================[Shiro身份认证结束]=====================");
        return new SimpleAuthenticationInfo(loginUser, token, getName());
    }

    /**
    * @Author: clw
    * @Description: 检验token的有效性
    * @Param: [token]
    * @return: com.clw.modules.sys.vo.LoginUser
    * @Date: 2021/3/12 23:00
    */
    private LoginUser checkUserTokenIsEffect(String token) {
        String username = JwtUtils.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token无效！");
        }

        // TODO 在这里可以添加 从Redis 里获取用户信息
        User user = userService.getOne(new LambdaQueryWrapper<>(User.class).eq(User::getUsername, username));
        LoginUser loginUser = new LoginUser();
        if (user == null) {
            throw new AuthenticationException("用户不存在！");
        }
        BeanUtils.copyProperties(user, loginUser);
        if (loginUser.getStatus() != 1) {
            throw new AuthenticationException("用户已冻结，请联系管理员！");
        }
        //校验token是否失效 && 密码是否错误
        if (!jwtTokenRefresh(token, username, loginUser.getPassword())) {
            throw new AuthenticationException("token失效，请重新登录");
        }
        return loginUser;
    }

    /**
    * @Author: clw
    * @Description:
     * JWTToken刷新生命周期  （实现：用户在线操作不掉线功能）
     * 1.登录成功后将用户的JWT生成的Token作为k、v存储到cache缓存里面（这时候k、v值一致），缓存有效期设置为Jwt有效时间的2倍
     * 2.当该用户再次请求时，
     * 3.
    * @Param: [token, username, password]
    * @return: boolean
    * @Date: 2021/3/12 23:44
    */
    private boolean jwtTokenRefresh(String token, String username, String password) {
        return true;
    }
}
