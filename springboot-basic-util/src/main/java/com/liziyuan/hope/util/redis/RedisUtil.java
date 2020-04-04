package com.liziyuan.hope.util.redis;

import com.liziyuan.hope.domain.constant.NumberConst;
import com.liziyuan.hope.domain.constant.StringConst;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis 的一些常用方法封装
 *
 * @author zqz
 * @version 1.0
 * @date 2020-04-02 21:51
 */

@Slf4j
@Component("redisUtil")
public class RedisUtil {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;


    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return 返回值成功还是失败
     */
    public boolean expire(String key, long time) {

        try {
            if (time > NumberConst.ZERO_LONG) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }


    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 返回值时间(秒) 返回代表为永久有效
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }


    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return 返回值true 存在 false不存在
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public void del(String... key) {
        if (key != null && key.length > NumberConst.ZERO) {

            if (key.length == NumberConst.ONE) {
                redisTemplate.delete(key[0]);

            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }


    // ============================String=============================

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 返回值值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);

    }

    /**
     * 普通缓存获取,类型为String
     *
     * @param key 键
     * @return 返回值值
     */
    public String getStrVal(String key) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        Object o = redisTemplate.opsForValue().get(key);
        if (null == o) {
            return null;
        }
        return (String) o;

    }


    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return 返回值true成功 false失败
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于 如果time小于等于 将设置无限期
     * @return 返回值true成功 false 失败
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > NumberConst.ZERO_LONG) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }


    /**
     * 递增
     *
     * @param key   键
     * @param delta 要增加几(大于)
     * @return 递增
     */
    public long incr(String key, long delta) {
        if (delta < NumberConst.ZERO_LONG) {
            throw new RuntimeException("递增因子必须大于0");
        }

        return redisTemplate.opsForValue().increment(key, delta);
    }


    /**
     * 递减
     *
     * @param key   键
     * @param delta 要减少几(小于)
     * @return 递减
     */
    public long decr(String key, long delta) {
        if (delta < NumberConst.ZERO_LONG) {
            throw new RuntimeException("递减因子必须大于");
        }

        return redisTemplate.opsForValue().increment(key, -delta);
    }


    // ================================Map=================================

    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 返回值值
     */
    public Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }


    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 返回值对应的多个键值
     */
    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }


    /**
     * HashSet
     *
     * @param key 键
     * @param map 对应多个键值
     * @return 返回值true 成功 false 失败
     */
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * HashSet 并设置时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return 返回值true成功 false失败
     */
    public boolean hmset(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > NumberConst.ZERO_LONG) {
                expire(key, time);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return 返回值true 成功 false失败
     */
    public boolean hset(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return 返回值true 成功 false失败
     */
    public boolean hset(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > NumberConst.ZERO_LONG) {
                expire(key, time);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }


    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 返回值true 存在 false不存在
     */
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }


    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于)
     * @return hash递增 如果不存在,就会创建一个 并把新增后的值返回
     */
    public double hincr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }


    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于)
     * @return hash递减
     */
    public double hdecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }


    // ============================set=============================

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return 根据key获取Set中的所有值
     */
    public Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return 返回值true 存在 false不存在
     */
    public boolean sHasKey(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 返回值成功个数
     */
    public long sSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);

        } catch (Exception e) {
            e.printStackTrace();
            return NumberConst.ZERO_LONG;
        }
    }


    /**
     * 将set数据放入缓存
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 返回值成功个数
     */
    public long sSetAndTime(String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);

            if (time > NumberConst.ZERO_LONG) {
                expire(key, time);
            }

            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return NumberConst.ZERO_LONG;
        }
    }


    /**
     * 获取set缓存的长度
     *
     * @param key 键
     * @return 获取set缓存的长度
     */
    public long sGetSetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);

        } catch (Exception e) {
            e.printStackTrace();
            return NumberConst.ZERO_LONG;
        }
    }


    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 返回值移除的个数
     */
    public long setRemove(String key, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;

        } catch (Exception e) {
            e.printStackTrace();
            return NumberConst.ZERO_LONG;

        }
    }

    // ===============================list=================================


    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束  到 -代表所有值
     * @return list缓存的内容
     */
    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取list缓存的长度
     *
     * @param key 键
     * @return list缓存的长度
     */
    public long lGetListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return NumberConst.ZERO_LONG;
        }
    }


    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引 index>=时，  表头， 第二个元素，依次类推；index<时，-，表尾，-倒数第二个元素，依次类推
     * @return 获取list中的值
     */
    public Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return 操作是否成功
     */
    public boolean lSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return 操作是否成功
     */
    public boolean lSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > NumberConst.ZERO_LONG) {
                expire(key, time);
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return 操作是否成功
     */
    public boolean lSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return 操作是否成功
     */
    public boolean lSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > NumberConst.ZERO_LONG) {
                expire(key, time);
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return 操作是否成功
     */
    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 返回值移除的个数
     */
    public long lRemove(String key, long count, Object value) {
        try {
            return redisTemplate.opsForList().remove(key, count, value);

        } catch (Exception e) {
            e.printStackTrace();
            return NumberConst.ZERO_LONG;

        }
    }

    /**
     * 加锁，短时间
     *
     * @param key 加锁的key
     * @return 是否加锁成功
     */
    public boolean addShortLock(String key) {
        // 默认的超时时间，30s
        return this.addLock(key, NumberConst.REDIS_LOCK_EXPIRE_SHORT_SECOND);
    }

    /**
     * 加锁，长时间
     *
     * @param key 加锁的key
     * @return 是否加锁成功
     */
    public boolean addLongLock(String key) {
        // 默认的超时时间，180s
        return this.addLock(key, NumberConst.REDIS_LOCK_EXPIRE_LONG_SECOND);
    }

    /**
     * 加锁，正常时间
     *
     * @param key 加锁的key
     * @return 是否加锁成功
     */
    public boolean addLock(String key) {
        // 默认的超时时间，60s
        return this.addLock(key, NumberConst.REDIS_LOCK_EXPIRE_SECOND);
    }

    /**
     * 加锁，自由时间
     *
     * @param key           加锁的key
     * @param expireSeconds 超时时间，秒
     * @return 是否加锁成功
     */
    public boolean addLock(String key, long expireSeconds) {
        if (StringUtils.isEmpty(key)) {
            // key为空，直接失败
            return false;
        }

        if (expireSeconds <= NumberConst.ZERO) {
            expireSeconds = NumberConst.REDIS_LOCK_EXPIRE_SECOND;
        }

        // 当前时间戳
        long currentMills = System.currentTimeMillis();

        // 加锁
        Boolean lockResult = redisTemplate.opsForValue().setIfAbsent(key, String.valueOf(currentMills));
        if (!lockResult) {
            // 加锁失败
            return false;
        }

        // 设置超时时间
        redisTemplate.expire(key, expireSeconds, TimeUnit.SECONDS);
        return true;
    }

    /**
     * 安全加锁
     *
     * @param key 加锁的key
     * @return 是否加锁成功
     */
    public boolean addLockSafe(String key) {
        // 默认的超时时间，60s
        return this.addLockSafe(key, NumberConst.REDIS_LOCK_EXPIRE_SECOND);
    }

    /**
     * 安全加锁
     *
     * @param key           加锁的key
     * @param expireSeconds 超时时间
     * @return 是否加锁成功
     */
    public boolean addLockSafe(String key, long expireSeconds) {
        if (StringUtils.isEmpty(key)) {
            // key为空，直接失败
            return false;
        }

        if (expireSeconds <= NumberConst.ZERO) {
            expireSeconds = NumberConst.REDIS_LOCK_EXPIRE_SECOND;
        }

        // 当前时间戳
        long currentMills = System.currentTimeMillis();

        // 安全方式
        final long times = expireSeconds;
        Boolean resultFlag = redisTemplate.execute((RedisCallback<Boolean>) redisConnection -> {
            Jedis jedis = (Jedis) redisConnection.getNativeConnection();
            String result = jedis.set(key, String.valueOf(currentMills), StringConst.SET_IF_NOT_EXIST, StringConst.SET_WITH_EXPIRE_TIME, times);

            // 返回结果
            return StringConst.LOCK_SUCCESS.equals(result);
        });

        // 防止为空的处理
        if (resultFlag == null) {
            // 加锁失败
            log.info("safe加锁失败，加锁的key为：{}", key);
            return false;
        }

        if (!resultFlag) {
            // 加锁失败
            log.info("safe加锁失败，加锁的key为：{}", key);
            return false;
        }

        // 加锁成功
        log.info("safe加锁成功，加锁的key为：{}", key);
        return true;
    }

    /**
     * 加锁，一个时间段内获得锁即认为加锁成功
     *
     * @param key 加锁的key
     * @return 是否加锁成功
     */
    public boolean addLockWithPeriod(String key) {
        return this.addLockWithPeriod(key, NumberConst.REDIS_LOCK_PERIOD_TIME);
    }

    /**
     * 加锁，一个时间段内获得锁即认为加锁成功
     *
     * @param key           加锁的key
     * @param periodSeconds 加锁有效时间段
     * @return 是否加锁成功
     */
    public boolean addLockWithPeriod(String key, long periodSeconds) {
        if (StringUtils.isEmpty(key)) {
            // key为空，直接失败
            return false;
        }

        // 时间段有效性
        if (periodSeconds <= NumberConst.ZERO) {
            periodSeconds = NumberConst.REDIS_LOCK_PERIOD_TIME;
        }

        // 时间段最大时间
        if (periodSeconds > NumberConst.REDIS_LOCK_MAX_PERIOD_TIME) {
            periodSeconds = NumberConst.REDIS_LOCK_MAX_PERIOD_TIME;
        }

        // 当前时间戳
        long currentMills = System.currentTimeMillis();
        // 应该截止的时间
        long startMills = currentMills;
        long endMills = startMills + periodSeconds * NumberConst.MILL_SECONDS;

        // 加锁结果
        boolean resultFlag = false;
        while (currentMills < endMills) {
            // 加锁结果
            resultFlag = this.addLock(key);
            // 如果成功立刻返回
            if (resultFlag) {
                long duringMills = currentMills - startMills;
                log.info("加锁成功，加锁的key为{}，加锁时间为{}ms", key, duringMills);
                return true;
            }

            // 等待1s
            try {
                TimeUnit.SECONDS.sleep(NumberConst.ONE_LONG);
            } catch (InterruptedException e) {
                log.error("addLockWithPeriod线程异常，异常原因：" + e);
                Thread.currentThread().interrupt();
                return false;
            }

            currentMills = System.currentTimeMillis();
        }

        // 能到这里，基本就是加锁失败
        log.info("到达等待时间，加锁失败，加锁的key为：{}", key);
        return false;
    }

    /**
     * 释放锁
     *
     * @param key 释放锁的key
     * @return 是否成功
     */
    public boolean releaseLock(String key) {
        Boolean hasKey = redisTemplate.hasKey(key);
        if (!hasKey) {
            // 没有锁，直接释放
            return true;
        }

        // 释放锁的结果
        boolean releaseResult = true;
        try {
            redisTemplate.delete(key);
        } catch (Exception e) {
            log.error("释放锁出现异常，异常原因为：{}", e.getMessage());
            releaseResult = false;
        }
        return releaseResult;
    }


}
