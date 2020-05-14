package cn.sits.rjb.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

/**
 * @author Neal.Sun
 * @version V1.0
 * @Package cn.sits.rjb.config
 * @date 2020/04/28 16:49
 */
@EnableAsync(proxyTargetClass = true)
@Configuration
public class AsyncConfig implements AsyncConfigurer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncConfig.class);

    @Override
    public Executor getAsyncExecutor() {
        return null;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncUncaughtExceptionHandler() {
            @Override
            public void handleUncaughtException(Throwable throwable, Method method, Object... params) {
//                LOGGER.error(throwable);
            }
        };
    }
}