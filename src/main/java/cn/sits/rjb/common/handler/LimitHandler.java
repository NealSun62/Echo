//package cn.sits.rjb.common.handler;
//
///**
// * @author Neal.Sun
// * @version V1.0
// * @Package cn.sits.rjb.common.handler
// * @date 2020/03/23 14:14
// */
//
//import cn.sits.rjb.common.aop.Limit;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.ibatis.binding.MapperMethod;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Objects;
//
///**
// * <p>
// * @description: 注解Limit的限制
// * </p>
// * @author: ZengGuangfu
// */
//
//@Slf4j
//@Aspect
//@Component
//public class LimitHandler {
//
//    @Autowired
//    RedisAuxiliary redisAuxiliary;
//
//    @Pointcut("@annotation(com.emperor.go.config.annocation.Limit)")
//    public void cut(){ }
//
//    @Around("cut()")
//    public Object around(ProceedingJoinPoint point) throws Throwable {
//        MapperMethod.MethodSignature methodSignature = (MapperMethod.MethodSignature) point.getSignature();
//        Object[] args = point.getArgs();
//        Limit limit = methodSignature.getMethod().getAnnotation(Limit.class);
//
//        Object result = null;
//        //获取request
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        if (Objects.nonNull(limit)){
//            // 获取参数
//            String key = limit.key();
//            String name = limit.name();
//            String prefix = limit.prefix();
//            int period = limit.period();
//            int count = limit.count();
//            SysEnum.LimitType limitType = limit.limitType();
//
//            StringBuffer redisKey = new StringBuffer("prefix_");
//            LoginVO lv = null;
//            if (args != null && args.length > 0 && args[0] instanceof LoginVO){
//                lv = (LoginVO) args[0];
//            }
//            switch (limitType){
//                case IP:
//                    redisKey.append(IPUtil.getIpAddr(request) + ":");
//                    break;
//                case SPECIAL:
//                    if (Objects.nonNull(lv)){
//                        redisKey.append(key).append("_" + lv.getLoginName() + ":");
//                    }
//                    break;
//            }
//            String currentKey = redisKey.toString();
//
//            /**
//             * 逻辑更改：
//             * 1.先查询redis有没有该记录，如果有，且数字大于 count 限制，即不能再登录
//             * 2.根据结果判断，如果登录成功，则清除缓存限制信息。如果失败了，则数字加一
//             */
//            try{
//                int anInt = 0;
//                Long number = redisAuxiliary.getLong(currentKey);
//                if (Objects.nonNull(number)){
//                    anInt = number.intValue();
//                }
//                if (anInt > count){
//                    throw new GlobalException(CodeMsg.LOGIN_TOO_MUCH);
//                }
//
//                result = point.proceed();
//                if (Objects.nonNull(result) && result instanceof String){
//                    if (result.equals("redirect:/main.html")){
//                        redisAuxiliary.delete(currentKey);
//                    }else if (result.equals("login")){
//                        redisAuxiliary.incrByExpireTime(currentKey, 1800);
//                    }
//                }
//            }catch (NumberFormatException e){
//                throw new GlobalException(CodeMsg.RUNTIME_ERROR.fillMsg("Limit AOP 数字转换异常，可能是缓存存储数据不是数字"));
//            }catch (GlobalException e){
//                throw e;
//            }catch (Exception e){
//                throw e;
//            }
//        }
//        return result;
//    }
//}