package cn.sits.rjb.system.service.impl;

import cn.sits.rjb.common.data.ResponseData;
import cn.sits.rjb.system.mapper.UserMapper;
import cn.sits.rjb.system.model.dto.LoginUserResponseDto;
import cn.sits.rjb.system.model.dto.LoginUserResquestDto;
import cn.sits.rjb.system.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class SysUserServiceImpl implements ISysUserService {
    private static final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Resource
    UserMapper userMapper;

    @Override
    public List<LoginUserResponseDto> findByLoginName(LoginUserResquestDto resquestDto) throws Exception {
        if (StringUtils.isNotEmpty(resquestDto.getLoginName())) {
            return userMapper.findByLoginName(resquestDto);
        } else {
            return null;
        }
    }

    @Override
    public List findUserByUserId(long userId) throws Exception {
        List userList = new ArrayList();
        try {
            userList = userMapper.findUserByUserId(userId);
        } catch (Exception e) {
            logger.error("查不到该userId{}", userId, e);
        }
        return userList;

    }

    //登录
    @Override
    public ResponseData login(String userName, String password) throws Exception {
        ResponseData response = null;
//        // 获取Subject实例对象，用户实例
//        Subject currentUser = SecurityUtils.getSubject();
//        // 将用户名和密码封装到UsernamePasswordToken
//        UsernamePasswordToken token = new UsernamePasswordToken(userName,MD5Util.string2MD5(password));
//        CacheUser cacheUser = new CacheUser();
//        // 4、认证
//        try {
//            // 传到 MyShiroRealm 类中的方法进行认证
//            currentUser.login(token);
//            // 构建缓存用户信息返回给前端
//            UserInfo user = (UserInfo) currentUser.getPrincipals().getPrimaryPrincipal();
//            cacheUser.setToken(currentUser.getSession().getId().toString()); // 获取当前用户sessionId
//            BeanUtils.copyProperties(user, cacheUser);
//            logger.info("CacheUser is {}"+cacheUser.toString());
//            response = new ResponseData(user,ResponseCodeEnum.SUCCESS.getCode(),"登陆成功");
//        } catch (UnknownAccountException e) {
//            logger.error("账户不存在异常："+e.getMessage());
//            response = new ResponseData(ResponseCodeEnum.FAILURE.getCode(),"账户不存在异常："+e.getMessage());
//        } catch (IncorrectCredentialsException e) {
//            logger.error("凭据错误（密码错误）异常："+e.getMessage());
//            response = new ResponseData(ResponseCodeEnum.FAILURE.getCode(),"凭据错误（密码错误）异常："+e.getMessage());
//        } catch (AuthenticationException e) {
//            logger.error("身份验证异常:"+e.getMessage());
//            response = new ResponseData(ResponseCodeEnum.FAILURE.getCode(),"身份验证异常:"+e.getMessage());
//        } catch (Exception e){
//            e.printStackTrace();
//            logger.error("其他错误"+e.getMessage());
//            response = new ResponseData(ResponseCodeEnum.FAILURE.getCode(),"其他错误"+e.getMessage());
//        }
        return response;
    }
}
