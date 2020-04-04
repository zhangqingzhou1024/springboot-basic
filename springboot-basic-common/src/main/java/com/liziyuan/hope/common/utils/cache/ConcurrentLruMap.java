package com.liziyuan.hope.common.utils.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Guava LoadingCache的封装
 *
 * @author zqz
 * @date 2019/12/10 17:20
 */
@Slf4j
public class ConcurrentLruMap<K, V> {

    private LoadingCache<K, CachedValue<V>> cacheMap;

    private int min;

    private int max;

    /**
     * 构造本地缓存
     *
     * @param minExpiresInSecond 最小时间
     * @param maxExpiresInSecond 最大时间
     * @param initSize           初识容量
     * @param maximumSize        最大容量
     */
    public ConcurrentLruMap(int minExpiresInSecond, int maxExpiresInSecond, int initSize, long maximumSize) {
        cacheMap = CacheBuilder.newBuilder()
                .initialCapacity(initSize)
                .maximumSize(maximumSize)
                // 写入后时效时间
                .expireAfterWrite(maxExpiresInSecond, TimeUnit.SECONDS)
                .build(defaultCacheLoader());
        this.min = minExpiresInSecond;
        this.max = maxExpiresInSecond;
    }

    /**
     * 默认刷新工具
     *
     * @return CacheLoader
     */
    private CacheLoader<K, CachedValue<V>> defaultCacheLoader() {
        return new CacheLoader<K, CachedValue<V>>() {
            @Override
            public CachedValue<V> load(K key) {
                return null;
            }
        };
    }

    /**
     * 写入方法
     *
     * @param key   key
     * @param value value
     */
    public boolean put(K key, V value) {
        if (key == null) {
            return false;
        }
        cacheMap.put(key, newCachedValue(value));
        return true;
    }

    /**
     * 构建Lru实例
     *
     * @param value value
     * @return lruValue
     */
    private CachedValue<V> newCachedValue(V value) {
        return new CachedValue<>(value, randomizeTimeout(min, max), currentTime());
    }

    /**
     * 随机超时时间
     *
     * @param min 最小超时时长
     * @param max 最大超时时长
     * @return 随机超时时间
     */
    private int randomizeTimeout(int min, int max) {
        return new Random().nextInt(max - min) + min;
    }

    /**
     * 取值
     *
     * @param key key
     * @return value
     */
    public V getValue(K key) {
        CachedValue<V> cachedValue = get(key);
        if (cachedValue == null) {
            return null;
        }
        return cachedValue.getValue();
    }

    /**
     * 获取LruValue<V>，可以通过LruValue判断是否传入了value=null
     *
     * @param key key
     * @return CachedValue<V>
     */
    private CachedValue<V> get(K key) {
        if (key == null) {
            return null;
        }
        CachedValue<V> cachedValue;
        try {
            cachedValue = cacheMap.get(key);
        } catch (Exception e) {
            return null;
        }
        if (cachedValue == null) {
            return null;
        }
        if (isValueExpired(cachedValue)) {
            remove(key);
            return null;
        }
        return cachedValue;
    }

    /**
     * 数据是否超时
     *
     * @return 超时为true
     */
    private boolean isValueExpired(CachedValue cachedValue) {
        return currentTime() - cachedValue.getInitTime() >= cachedValue.getTimeout();
    }

    /**
     * 获取当前系统时间
     *
     * @return 当前时间时间
     */
    private long currentTime() {
        return System.currentTimeMillis() / 1000L;
    }

    /**
     * 移除元素
     *
     * @param key key
     */
    public void remove(K key) {
        cacheMap.invalidate(key);
    }

    /**
     * 包含key
     *
     * @param key key
     * @return 包含key
     */
    public boolean containsKey(K key) {
        return cacheMap.asMap().containsKey(key);
    }

    /**
     * 包含的元素数量, 约数，计算数量时并不能剔除超时的元素
     *
     * @return 包含的元素数量的约数
     */
    public long size() {
        return cacheMap.size();
    }

    /**
     * 有效的key集合
     *
     * @return 有效的key集合
     */
    public Set<K> validKeySet() {
        return cacheMap.asMap().entrySet().stream().filter(Objects::nonNull)
                .filter(entry -> !isValueExpired(entry.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    /**
     * 对value的一个封装
     *
     * @param <V>
     */
    private class CachedValue<V> {
        private V value;
        private long initTime;

        private int timeout;

        public CachedValue(V value) {
            this.value = value;
        }

        CachedValue(V value, int timeout, long initTime) {
            this.value = value;
            this.timeout = timeout;
            this.initTime = initTime;
        }

        public V getValue() {
            return value;
        }

        void setValue(V value) {
            this.value = value;
        }

        long getInitTime() {
            return initTime;
        }

        int getTimeout() {
            return timeout;
        }
    }

}
