package com.clw.shiro.cache;

import com.clw.util.ApplicationContextUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;

/**
 * @Author: clw
 * @Description: 自定义Redis缓存的实现
 * @Date: 2021/3/2 21:27
 */
@Component
public class RedisCache<K, V> implements Cache<K, V> {

    private String cacheName;

    public RedisCache() {

    }

    public RedisCache(String cacheName) {
        this.cacheName = cacheName;
    }

    @Override
    public V get(K k) throws CacheException {
        System.out.println("======= get key : " + k + " =======");
        // return (V) getRedisTemplate().opsForValue().get(k.toString());
        return (V) getRedisTemplate().opsForHash().get(cacheName, k.toString());
    }

    @Override
    public V put(K k, V v) throws CacheException {
        System.out.println("======= put key : " + k + " =======");
        System.out.println("======= put value : " + v + " =======");

        // getRedisTemplate().opsForValue().set(k.toString(), v);
        getRedisTemplate().opsForHash().put(cacheName, k.toString(), v);
        return null;
    }

    @Override
    public V remove(K k) throws CacheException {
        System.out.println("======= remove key : " + k + " =======");
        return (V) getRedisTemplate().opsForHash().delete(cacheName, k.toString());
    }

    @Override
    public void clear() throws CacheException {
        System.out.println("======= clear key : =======");
        getRedisTemplate().delete(cacheName);
    }

    @Override
    public int size() {
        return getRedisTemplate().opsForHash().size(cacheName).intValue();
    }

    @Override
    public Set<K> keys() {
        return getRedisTemplate().keys(cacheName);
    }

    @Override
    public Collection<V> values() {
        return getRedisTemplate().opsForHash().values(cacheName);
    }

    private RedisTemplate getRedisTemplate() {
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
