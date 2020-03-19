package cn.sits.rjb.system.service.impl;

import cn.sits.rjb.common.data.ResponseData;
import cn.sits.rjb.common.enums.ResponseCodeEnum;
import cn.sits.rjb.common.utils.MD5Util;
import cn.sits.rjb.config.shiro.CacheUser;
import cn.sits.rjb.system.mapper.UserMapper;
import cn.sits.rjb.system.model.po.SysUser;
import cn.sits.rjb.system.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Primary
public class SysUserServiceImpl implements ISysUserService {
    private static final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

//    @Autowired(required=false)
    @Resource
    UserMapper userMapper;

    @Override
    public SysUser findBySysUserCode(String userCode){
        if (StringUtils.isNotEmpty(userCode)) {
            return userMapper.findByUserCodeAndActive(userCode);
        }else {
            return null;
        }
    }

    //登录
    @Override
    public ResponseData login(String userName, String password) throws Exception{
        ResponseData response = null;
        // 获取Subject实例对象，用户实例
        Subject currentUser = SecurityUtils.getSubject();
        // 将用户名和密码封装到UsernamePasswordToken
        UsernamePasswordToken token = new UsernamePasswordToken(userName,MD5Util.string2MD5(password));
        CacheUser cacheUser = new CacheUser();
        // 4、认证
        try {
            // 传到 MyShiroRealm 类中的方法进行认证
            currentUser.login(token);
            // 构建缓存用户信息返回给前端
            SysUser user = (SysUser) currentUser.getPrincipals().getPrimaryPrincipal();
            cacheUser.setToken(currentUser.getSession().getId().toString()); // 获取当前用户sessionId
            BeanUtils.copyProperties(user, cacheUser);
            logger.info("CacheUser is {}"+cacheUser.toString());
            response = new ResponseData(user,ResponseCodeEnum.SUCCESS.getCode(),"登陆成功");
        } catch (UnknownAccountException e) {
            logger.error("账户不存在异常："+e.getMessage());
            response = new ResponseData(ResponseCodeEnum.FAILURE.getCode(),"账户不存在异常："+e.getMessage());
        } catch (IncorrectCredentialsException e) {
            logger.error("凭据错误（密码错误）异常："+e.getMessage());
            response = new ResponseData(ResponseCodeEnum.FAILURE.getCode(),"凭据错误（密码错误）异常："+e.getMessage());
        } catch (AuthenticationException e) {
            logger.error("身份验证异常:"+e.getMessage());
            response = new ResponseData(ResponseCodeEnum.FAILURE.getCode(),"身份验证异常:"+e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
            logger.error("其他错误"+e.getMessage());
            response = new ResponseData(ResponseCodeEnum.FAILURE.getCode(),"其他错误"+e.getMessage());
        }
        return response;
    }
}
