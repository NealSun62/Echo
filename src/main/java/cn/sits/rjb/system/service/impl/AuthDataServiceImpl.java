package cn.sits.rjb.system.service.impl;

import cn.sits.rjb.common.data.ResponseData;
import cn.sits.rjb.common.enums.ResponseCodeEnum;
import cn.sits.rjb.common.utils.JsonUtil;
import cn.sits.rjb.common.utils.StringUtil;
import cn.sits.rjb.common.utils.TokenUtil;
import cn.sits.rjb.constants.Constants;
import cn.sits.rjb.system.model.dto.AuthDataResponseDto;
import cn.sits.rjb.system.model.po.SysAuth;
import cn.sits.rjb.system.service.IAuthDataService;
import cn.sits.rjb.system.service.ISysUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by bf on 2018/12/18.
 */
@Service
@Primary
public class AuthDataServiceImpl implements IAuthDataService {
    private static final Logger logger = LoggerFactory.getLogger(AuthDataServiceImpl.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ISysUserService iSysUserService;

    @Override
    public AuthDataResponseDto getAuthData(Long userId) throws Exception {
        AuthDataResponseDto dto = null;
        String json = stringRedisTemplate.opsForValue().get(Constants.AUTHDATARESPONSEDTO + userId);
        if (!StringUtil.isEmpty(json) && !"[]".equals(json)) {
            dto = JsonUtil.fromJson(json, AuthDataResponseDto.class);
        }
        if (dto == null) {
            dto = getAuthDataFromDataBase(userId);
        }
        return dto;
    }

    @Override
    public AuthDataResponseDto getAuthDataFromDataBase(Long userId) throws Exception {
        AuthDataResponseDto dto = null;

        try {
            // 获取当前登录人信息
//            UserInfo user = iSysUserService.findUserByUserId(userId);
//            if (user == null) {
//                return dto;
//            }
            ObjectMapper mapper = new ObjectMapper();
            // 获取角色 集合
//            List<Map> roleMapListCheckAdmin = roleRepository.findRoleListByUserId(userId);
//            List<role> roleListCheckAdmin = mapper.convertValue(roleMapListCheckAdmin, new TypeReference<List<role>>() {
//            });
            // 判断 登录人是不是最高权限的 管理员
//            String admin = "0";
//            if (roleListCheckAdmin != null && roleListCheckAdmin.size() > 0) {
//                for (role r : roleListCheckAdmin) {
//                    if ("admin".equals(r.getRlnam())) {
//                        admin = "1";
//                        break;
//                    }
//                }
//            }
//            if ("1".equals(admin)) {
//                dto = new AuthDataResponseDto();
//                dto.setAdmin(admin);
//                stringRedisTemplate.opsForValue().set(Constants.AUTHDATARESPONSEDTO + userId, JsonUtil.toJson(dto), Constants.SAVE_REDIS_DATA_TIMEOUT, TimeUnit.SECONDS);
//                return dto;
//            }
//
//            // 获取区域 集合
//        List<Map> areaMapList = areaRepository.findAreaListByUserId(userId, "");
//        List<area> areaList = mapper.convertValue(areaMapList, new TypeReference<List<area>>() {
//        });
//        List<Long> areaIdList = new ArrayList<>();
//        if(areaList!=null&&areaList.size()>0){
//            for(area r:areaList){
//                areaIdList.add(r.getAreid());
//            }
//        }
            // 获取公司 集合
//        List<Map> unitMapList = orgunitRepository.findUnitListByUserId(userId, "");
//        List<orgunit> unitList = mapper.convertValue(unitMapList, new TypeReference<List<orgunit>>() {
//        });
//        List<Long> unitIdList = new ArrayList<>();
//        if(unitList!=null&&unitList.size()>0){
//            for(orgunit r:unitList){
//                unitIdList.add(r.getUntid());
//            }
//        }
            // 获取权限 集合
//            List<Map> authMapList = authRepository.findAuthListByUserId(userId, "");
//            List<auth> authList = mapper.convertValue(authMapList, new TypeReference<List<auth>>() {
//            });
//            List<Long> authIdList = new ArrayList<>();
//            if (authList != null && authList.size() > 0) {
//                for (auth r : authList) {
//                    authIdList.add(r.getAutid());
//                }
//            }
//            // 获取角色 集合
//            List<Map> roleMapList = new ArrayList<>();
//            if (unitIdList.size() > 0) {
//                roleMapList = roleRepository.findExistRoleListByUserId(unitIdList, "");
//            }
//            List<role> roleList = mapper.convertValue(roleMapList, new TypeReference<List<role>>() {
//            });
//            List<Long> roleIdList = new ArrayList<>();
//            if (roleList != null && roleList.size() > 0) {
//                for (role r : roleList) {
//                    roleIdList.add(r.getRleid());
//                }
//            }
//            dto = new AuthDataResponseDto();
//            dto.setRoleList(roleList);
//        dto.setAreaList(areaList);
//            dto.setAuthList(authList);
//        dto.setUnitList(unitList);
//        dto.setAreaIdList(areaIdList);
//            dto.setAuthIdList(authIdList);
//            dto.setRoleIdList(roleIdList);
//            dto.setUnitIdList(unitIdList);
            stringRedisTemplate.opsForValue().set(Constants.AUTHDATARESPONSEDTO + userId, JsonUtil.toJson(dto), Constants.SAVE_REDIS_DATA_TIMEOUT, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.error("getAuthDataFromDataBase ERROR", e);
        }
        return dto;
    }

    @Override
    public ResponseData checkOperation(HttpServletRequest request, String opt, String[] sourceOptArray) throws Exception {
        if (opt == null || "".equals(opt)) {
            return new ResponseData(ResponseCodeEnum.COMMON_ERROR_2.getCode(), ResponseCodeEnum.COMMON_ERROR_2.getMsg());
        }
        // 匹配 当前路径下的操作对象
        boolean flag = false;
        for (String sourceOpt : sourceOptArray) {
            if (sourceOpt.equals(opt)) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            return new ResponseData(ResponseCodeEnum.COMMON_ERROR_2.getCode(), ResponseCodeEnum.COMMON_ERROR_2.getMsg());
        }
        // 获取用户ID
        Long userId = TokenUtil.getUserId(request);
        if (userId.longValue() == 0) {
            return new ResponseData(ResponseCodeEnum.COMMON_ERROR_1.getCode(), ResponseCodeEnum.COMMON_ERROR_1.getMsg());
        }
        // 获取 用户拥有的操作权限
        AuthDataResponseDto authDataResponseDto = this.getAuthData(userId);
        if (authDataResponseDto == null) {
            return new ResponseData(ResponseCodeEnum.COMMON_ERROR_1.getCode(), ResponseCodeEnum.COMMON_ERROR_1.getMsg());
        }
        List<SysAuth> sysAuthList = authDataResponseDto.getAuthList();
        if (sysAuthList == null || sysAuthList.size() == 0) {
            return new ResponseData(ResponseCodeEnum.COMMON_ERROR_2.getCode(), ResponseCodeEnum.COMMON_ERROR_2.getMsg());
        }
        // 校验用户当前的操作权限
        String[] optArray = opt.split("_");
        for (SysAuth sysAuth : sysAuthList) {
            if (sysAuth.getAuthGroup().equals(optArray[0]) && sysAuth.getAuthCode().equals(optArray[1])) {
                // 更新 数据库中 权限等信息的缓存时间
                // stringRedisTemplate.opsForValue().set(Constants.AUTHDATARESPONSEDTO + userId, JsonUtil.toJson(authDataResponseDto), Constants.TOKEN_REDIS_DATA_TIMEOUT, TimeUnit.SECONDS);
                stringRedisTemplate.opsForValue().set(Constants.AUTHDATARESPONSEDTO + userId, JsonUtil.toJson(authDataResponseDto));
                return new ResponseData(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getMsg());
            }
        }
        return new ResponseData(ResponseCodeEnum.COMMON_ERROR_2.getCode(), ResponseCodeEnum.COMMON_ERROR_2.getMsg());
    }
}
