package cn.sits.rjb.system.service;


import cn.sits.rjb.common.data.ResponseData;
import cn.sits.rjb.system.model.dto.AuthDataResponseDto;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by bf on 2018/12/18.
 */
public interface IAuthDataService {
    /**
     * 校验用户是否有操作权限
     * @param request
     * @param opt
     * @return
     * @throws Exception
     */
    ResponseData checkOperation(HttpServletRequest request, String opt, String[] sourceOptArray)throws Exception;

    AuthDataResponseDto getAuthData(Long userId)throws Exception;
    AuthDataResponseDto getAuthDataFromDataBase(Long userId)throws Exception;
}
