package com.clw.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * @Author: clw
 * @Description: 自定义Shiro缓存管理器
 * @Date: 2021/3/2 21:08
 */
public class RedisCacheManager implements CacheManager {
    
    /**
    * @Author: clw
    * @Description:
    * @Param: [cacheName] [认证或者授权缓存的统一名称]
    * @return: org.apache.shiro.cache.Cache<K,V>
    * @Date: 2021/3/2 21:24
    */
    @Override
    public <K, V> Cache<K, V> getCache(String cacheName) throws CacheException {
        System.out.println("============    RedisCacheManager   ============");
        System.out.println("============    getCache(s)参数:    " + cacheName +"    ============");
        return new RedisCache<K, V>(cacheName);
    }
}
