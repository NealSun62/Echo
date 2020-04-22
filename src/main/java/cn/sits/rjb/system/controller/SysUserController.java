package cn.sits.rjb.system.controller;

import cn.sits.rjb.common.data.ResponseData;
import cn.sits.rjb.common.enums.ResponseCodeEnum;
import cn.sits.rjb.common.utils.PBKDF2Util;
import cn.sits.rjb.common.utils.VerifyUtil;
import cn.sits.rjb.config.authentication.TokenManager;
import cn.sits.rjb.system.model.dto.AuthDataResponseDto;
import cn.sits.rjb.system.model.dto.LoginUserResponseDto;
import cn.sits.rjb.system.model.dto.LoginUserResquestDto;
import cn.sits.rjb.system.service.IAuthDataService;
import cn.sits.rjb.system.service.ISysUserService;
import com.alibaba.druid.util.StringUtils;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    private IAuthDataService iAuthDataService;
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
                //验证码
//                String validateCode = loginRequestDto.getValidateCode().toUpperCase();
//                String validateCodeSession = stringRedisTemplate.opsForValue().get(VerifyUtil.RANDOMCODEKEY+validateCode);
//                if(validateCodeSession==null||!validateCode.toLowerCase().equals(validateCodeSession.toLowerCase())){
//                    return new ResponseData(BizCodeEnum.COMMON_ERROR_200.getCode(),BizCodeEnum.COMMON_ERROR_200.getMsg());
//                }else{
//                    stringRedisTemplate.delete(VerifyUtil.RANDOMCODEKEY+validateCode);
//                }
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
                    AuthDataResponseDto authDataResponseDto = iAuthDataService.getAuthData(loginUserResponseDto.getUserId());
                    loginUserResponseDto.setToken(token);
                    loginUserResponseDto.setUserId(Integer.parseInt(userId));
                    loginUserResponseDto.setPassword("");
                    loginUserResponseDto.setSalt("");
//                    loginResponseDto.setAuthDataResponseDto(authDataResponseDto);
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

    /**
     * 进入登录页面并且产生验证码
     * louis.lu
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getvalidatecode")
    @ApiOperation(value="验证码", notes="获取验证码")
    public void getValidateCode(HttpServletRequest request, HttpServletResponse response) {
        // 进入登录页面  产生  验证码
        try {
            response.setCharacterEncoding("utf-8");
            logger.info("开始生成验证码");
            response.setContentType("image/png;charset=UTF-8");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            VerifyUtil randomValidateCode = new VerifyUtil(stringRedisTemplate);
            randomValidateCode.getRandcode(request, response);//输出验证码图片
//			ValidateCodeUtil randomValidateCode1 = new ValidateCodeUtil();
//			randomValidateCode1.getRandcode(request, response);//输出验证码图片方法
        } catch (Exception e) {
            logger.error("获取验证码失败>>>>   ", e);
        }
    }
}
