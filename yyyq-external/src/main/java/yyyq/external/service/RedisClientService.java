package yyyq.external.service;

import java.util.Map;

/**
 * RedisClientService
 *
 * @author liyunzhi
 * @date 2018/7/29 16:26
 */
public interface RedisClientService {
    void setStringToRedis(String key, String value);

    void setStringToRedis(String key, String value, long time);

    String getStringFromRedis(String key);

    <T> void setObjectToRedis(String key, T t);

    <T> void setObjectToRedis(String key, T t, long time);

    <T> T  getObjectFromRedis(String key, Class<T> clazz);

    <T> T getAndUpdateObject(String key, Class<T> clazz, long time);

    boolean hasKey(String key);

    void deleteKey(String key);

    void setHashToRedis(String key, Map<String, Object> value, long time);

    <T> T getHashObjectFromRedis(String key, Class<T> clazz);

    Object getHashObjectFromRedis(String key, String objectKey);
}
