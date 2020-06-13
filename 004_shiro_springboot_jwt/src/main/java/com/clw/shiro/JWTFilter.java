package com.clw.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: clw
 * @Description: JWTFilter 实现前端请求统一拦截及处理
 * @Date: 2020/6/6 17:08
 */
public class JWTFilter extends BasicHttpAuthenticationFilter {

    /**
     * 认证之前执行该方法
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = SecurityUtils.getSubject();
        return  null != subject && subject.isAuthenticated();
    }

    /**
     * 认证未通过执行该方法
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response){
        //完成token登入
        //1.检查请求头中是否含有token
        HttpServletRequest httpServletRequest= (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("Authorization");
        //2. 如果客户端没有携带token，拦下请求
        if(null==token||"".equals(token)){
            System.out.println("Token无效，您无权访问该接口");
            return false;
        }
        //3. 如果有，对进行进行token验证
        JWTToken jwtToken = new JWTToken(token);
        try {
            SecurityUtils.getSubject().login(jwtToken);
        } catch (AuthenticationException e) {
            System.out.println(".............. " +e.getMessage());
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
    ///**
    // * 无需转发，直接返回Response信息 Token认证错误
    // */
    //private void responseTokenError(ServletResponse response, String msg) {
    //    HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
    //    httpServletResponse.setStatus(HttpStatus.OK.value());
    //    httpServletResponse.setCharacterEncoding("UTF-8");
    //    httpServletResponse.setContentType("application/json; charset=utf-8");
    //    try (PrintWriter out = httpServletResponse.getWriter()) {
    //        String data = new Gson().toJson(new ResponseBean(4001,   msg, null));
    //        out.append(data);
    //    } catch (IOException e) {
    //        e.printStackTrace();
    //        log.error(e.getMessage());
    //    }
    //}

    //// 登录标识
    //private static String LOGIN_SIGN = "Authorization";
    //
    //@Override
    //protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
    //    System.out.println("isAccessAllowed...");
    //    if(isLoginAttempt(request, response)) {
    //        try {
    //            executeLogin(request, response);
    //        } catch (Exception e) {
    //            System.out.println(".............. 权限不足..............");
    //            e.printStackTrace();
    //        }
    //    }
    //    return super.isAccessAllowed(request, response, mappedValue);
    //}
    //
    ///**
    // * 检测用户是否登录
    // * 检测header里面是否包含Authorization字段
    // * @param request
    // * @param response
    // * @return
    // */
    //@Override
    //protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
    //    System.out.println("isLoginAttempt...");
    //    HttpServletRequest req = (HttpServletRequest) request;
    //    String authorization = req.getHeader(LOGIN_SIGN);
    //    return authorization != null;
    //}
    //
    ///**
    // * executeLogin() 方法中的 getSubject(request, response).login(token) 就是触发 Shiro Realm 自身的登录控制，具体内容需要手动实现
    // * 始终返回 true 的原因是因为具体的是否登录成功的判断，需要在 Realm 中手动实现，此处不做统一判断
    // * @param request
    // * @param response
    // * @return
    // * @throws Exception
    // */
    //@Override
    //protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
    //    System.out.println("executeLogin...");
    //    HttpServletRequest req = (HttpServletRequest) request;
    //    String authorization = req.getHeader(LOGIN_SIGN);
    //    JWTToken token = new JWTToken(authorization);
    //    getSubject(request,response).login(token);
    //    return true;
    //}
    //
    ///**
    // * 对跨域提供支持
    // * @param request
    // * @param response
    // * @return
    // * @throws Exception
    // */
    //@Override
    //protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
    //    System.out.println("preHandle...");
    //    HttpServletRequest req = (HttpServletRequest) request;
    //    HttpServletResponse res = (HttpServletResponse) response;
    //    res.setHeader("Access-control-Allow-Origin", req.getHeader("Origin"));
    //    res.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
    //    res.setHeader("Access-Control-Allow-Headers", req.getHeader("Access-Control-Request-Headers"));
    //    // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
    //    if (req.getMethod().equals(RequestMethod.OPTIONS.name())) {
    //        res.setStatus(HttpStatus.OK.value());
    //        return false;
    //    }
    //    return super.preHandle(request, response);
    //}
}
