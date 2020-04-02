package cn.sits.rjb.system.controller;

import cn.sits.rjb.common.data.ResponseData;
import cn.sits.rjb.common.enums.ResponseCodeEnum;
import cn.sits.rjb.common.utils.PBKDF2Util;
import cn.sits.rjb.config.authentication.TokenManager;
import cn.sits.rjb.system.model.dto.LoginUserResponseDto;
import cn.sits.rjb.system.model.dto.LoginUserResquestDto;
import cn.sits.rjb.system.service.ISysUserService;
import com.alibaba.druid.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yuhongjie
 * @version 1.0
 * @date 2019-11-12 9:14
 */
@RestController
@RequestMapping("/SysUser")
public class SysUserController {
    private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private ISysUserService iSysUserService;

//    @Autowired
//    private RedisUtil stringRedisTemplate;
    
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private TokenManager tokenManager;

    /**
     * 登录
     *
     * @return BaseResponse
     */
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public ResponseData login(@RequestBody LoginUserResquestDto loginUserResquestDto) {
        ResponseData response = null;
        try {
            if (loginUserResquestDto != null &&
                    !StringUtils.isEmpty(loginUserResquestDto.getLoginName()) &&
                    !StringUtils.isEmpty(loginUserResquestDto.getLoginPassword())) {
                String token = "";
                String loginPassword = loginUserResquestDto.getLoginPassword();
                List<LoginUserResponseDto> userList = iSysUserService.findByLoginName(loginUserResquestDto);
                if (userList.isEmpty()) {
                    return new ResponseData(ResponseCodeEnum.FAILURE.getCode(), "查无此人");
                }
                LoginUserResponseDto loginUserResponseDto = userList.get(0);
                String userId = String.valueOf(loginUserResponseDto.getUserId());
                String salt = loginUserResponseDto.getSalt();
                String password = loginUserResponseDto.getPassword();
                boolean result = PBKDF2Util.authenticate(loginPassword, password, salt);
                if (!result) {
                    return new ResponseData(ResponseCodeEnum.FAILURE.getCode(), "账号密码错误");
                } else {
                    stringRedisTemplate.delete(String.valueOf(userId));
                    tokenManager.createToken(userId);
                    token = stringRedisTemplate.opsForValue().get(userId).toString();
                    if (StringUtils.isEmpty(token)) {
                        return new ResponseData("token生成失败", ResponseCodeEnum.FAILURE.getCode(), ResponseCodeEnum.FAILURE.getMsg());
                    }
                    loginUserResponseDto.setToken(token);
                    loginUserResponseDto.setUserId(Integer.parseInt(userId));
                    loginUserResponseDto.setPassword("");
                    loginUserResponseDto.setSalt("");
                    response = new ResponseData(loginUserResponseDto,ResponseCodeEnum.SUCCESS.getCode(), "登入成功");
                }
            } else{
                logger.error("操作失败！传值为空");
                response = new ResponseData(ResponseCodeEnum.FAILURE.getCode(), "传值为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseData(ResponseCodeEnum.FAILURE.getCode(), e.getMessage());
        }
        return response;
    }


}
