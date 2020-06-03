package com.clw.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/6/3 19:02
 */
public class MySessionListener extends SessionListenerAdapter {
    /**
     * session创建时触发
     * @param session
     */
    @Override
    public void onStart(Session session) {
        System.out.println("session create...");
    }

    /**
     * session停止时触发   subject.logout()   session.stop()
     * @param session
     */
    @Override
    public void onStop(Session session) {
        System.out.println("session stop...");
    }

    /**
     * session过期时触发
     * @param session
     */
    @Override
    public void onExpiration(Session session) {
        System.out.println("session expiration...");
    }
}
