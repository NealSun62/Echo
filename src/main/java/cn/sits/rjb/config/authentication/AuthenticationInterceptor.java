package cn.sits.rjb.config.authentication;

import cn.sits.rjb.constants.Constants;
import com.alibaba.druid.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;


/**
 * 用于检查 token 的拦截器
 *
 * @since 1.0.0
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationInterceptor.class);
	 
    private TokenManager tokenManager;

    public void setTokenManager(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	LOGGER.info("进入AuthenticationInterceptor......"+request.getRequestURL());
    	// 从切点上获取目标方法
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 若目标方法忽略了安全性检查，则直接调用目标方法

        if (method.isAnnotationPresent(IgnoreAuthentication.class)) {
            return true;
        }
        // 从 request header 中获取当前 token
        String token = request.getParameter(Constants.TOKEN_NAME);
        String userId = request.getParameter("userId");
        Map map = request.getParameterMap();
        // 检查 token 有效性
        if (!StringUtils.isEmpty(userId)&&!StringUtils.isEmpty(token)&&!tokenManager.checkToken(userId,token)) {
            String message = String.format("token [%s] is invalid", token);
            throw new AuthenticationException(message);
        }
        // 调用目标方法
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
