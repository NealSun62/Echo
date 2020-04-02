package cn.sits.rjb.config.authentication.impl;

import cn.sits.rjb.config.authentication.TokenManager;
import cn.sits.rjb.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 基于Redis 的Token管理器
 * 
 * @description 将 token(UUID) 存储到 Redis中
 * @author louis.lu
 * @since 1.0.0
 */

@Service
@Primary
public class RedisTokenManager implements TokenManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisTokenManager.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String createToken(String userId) {
        String token = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();;
        try {
        	stringRedisTemplate.opsForValue().set(userId, token, Constants.SAVE_REDIS_DATA_TIMEOUT,TimeUnit.SECONDS);
        } catch (Exception e) {
            LOGGER.error("在Redis中创建Token失败！", e);
        }
        return token;
    }

    @Override
    public boolean checkToken(String userId,String checkTokenString) {
        boolean result = false;
        String token = stringRedisTemplate.opsForValue().get(userId);
        if(token!=null&&token.equals(checkTokenString)){
            stringRedisTemplate.opsForValue().set(userId, token, Constants.SAVE_REDIS_DATA_TIMEOUT,TimeUnit.SECONDS);
            String json = stringRedisTemplate.opsForValue().get(Constants.AUTHDATARESPONSEDTO + userId);
            if(json!=null){
                stringRedisTemplate.opsForValue().set(userId, json, Constants.SAVE_REDIS_DATA_TIMEOUT,TimeUnit.SECONDS);
            }
            result = true;
        }
        return result;
    }

}
