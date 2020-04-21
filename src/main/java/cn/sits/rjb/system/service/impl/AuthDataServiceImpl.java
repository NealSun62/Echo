package cn.sits.rjb.system.service.impl;

import cn.sits.rjb.common.data.ResponseData;
import cn.sits.rjb.common.enums.ResponseCodeEnum;
import cn.sits.rjb.common.utils.JsonUtil;
import cn.sits.rjb.common.utils.TokenUtil;
import cn.sits.rjb.constants.Constants;
import cn.sits.rjb.system.model.dto.AuthDataResponseDto;
import cn.sits.rjb.system.model.po.SysAuth;
import cn.sits.rjb.system.service.IAuthDataService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by bf on 2018/12/18.
 */
@Service
public class AuthDataServiceImpl implements IAuthDataService{
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public List<Long> getRampIdList(HttpServletRequest request) throws Exception{
        Long userId = TokenUtil.getUserId(request);
        AuthDataResponseDto dto = null;
        String json = stringRedisTemplate.opsForValue().get(Constants.AUTHDATARESPONSEDTO + userId);
        if (!StringUtils.isEmpty(json) && !"[]".equals(json)) {
            dto = JsonUtil.fromJson(json, AuthDataResponseDto.class);
        }
        if(dto!=null){
            return dto.getRampIdList();
        }
        return null;
    }
    @Override
    public AuthDataResponseDto getAuthData(Long userId) {
        AuthDataResponseDto dto = null;
        String json = stringRedisTemplate.opsForValue().get(Constants.AUTHDATARESPONSEDTO + userId);
        if (!StringUtils.isEmpty(json) && !"[]".equals(json)) {
            dto = JsonUtil.fromJson(json, AuthDataResponseDto.class);
        }
        return dto;
    }

    @Override
    public ResponseData checkOperation(HttpServletRequest request, String opt, String[] sourceOptArray)throws Exception {
        if(opt==null||"".equals(opt)){
            return new ResponseData(ResponseCodeEnum.COMMON_ERROR_2.getCode(),ResponseCodeEnum.COMMON_ERROR_2.getMsg());
        }
        // 匹配 当前路径下的操作对象
        boolean flag = false;
        for(String sourceOpt:sourceOptArray){
            if(sourceOpt.equals(opt)){
                flag = true;
                break;
            }
        }
        if(!flag){
            return new ResponseData(ResponseCodeEnum.COMMON_ERROR_2.getCode(),ResponseCodeEnum.COMMON_ERROR_2.getMsg());
        }
        // 获取用户ID
        Long userId = TokenUtil.getUserId(request);
        if(userId.longValue()==0){
            return new ResponseData(ResponseCodeEnum.COMMON_ERROR_1.getCode(),ResponseCodeEnum.COMMON_ERROR_1.getMsg());
        }
        // 获取 用户拥有的操作权限
        AuthDataResponseDto authDataResponseDto = this.getAuthData(userId);
        if(authDataResponseDto==null){
            return new ResponseData(ResponseCodeEnum.COMMON_ERROR_1.getCode(),ResponseCodeEnum.COMMON_ERROR_1.getMsg());
        }
        List<SysAuth> sysAuthList = authDataResponseDto.getAuthList();
        if(sysAuthList==null||sysAuthList.size()==0){
            return new ResponseData(ResponseCodeEnum.COMMON_ERROR_2.getCode(),ResponseCodeEnum.COMMON_ERROR_2.getMsg());
        }
        // 校验用户当前的操作权限
        String[] optArray = opt.split("_");
        for(SysAuth sysAuth:sysAuthList){
            if(sysAuth.getAuthGroup().equals(optArray[0])&&sysAuth.getAuthCode().equals(optArray[1])){
                // 更新 数据库中 权限等信息的缓存时间
                // stringRedisTemplate.opsForValue().set(Constants.AUTHDATARESPONSEDTO + userId, JsonUtil.toJson(authDataResponseDto), Constants.TOKEN_REDIS_DATA_TIMEOUT, TimeUnit.SECONDS);
                stringRedisTemplate.opsForValue().set(Constants.AUTHDATARESPONSEDTO + userId, JsonUtil.toJson(authDataResponseDto));
                return new ResponseData(ResponseCodeEnum.SUCCESS.getCode(),ResponseCodeEnum.SUCCESS.getMsg());
            }
        }
        return new ResponseData(ResponseCodeEnum.COMMON_ERROR_2.getCode(),ResponseCodeEnum.COMMON_ERROR_2.getMsg());
    }
}
