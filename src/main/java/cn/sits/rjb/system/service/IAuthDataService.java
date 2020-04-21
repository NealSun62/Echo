package cn.sits.rjb.system.service;


import cn.sits.rjb.common.data.ResponseData;
import cn.sits.rjb.system.model.dto.AuthDataResponseDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by bf on 2018/12/18.
 */
public interface IAuthDataService {
    /**
     * 根据token获取数据权限
     * @param request
     * @return
     * @throws Exception
     */
    List<Long> getRampIdList(HttpServletRequest request) throws Exception;

    /**
     * 校验用户是否有操作权限
     * @param request
     * @param opt
     * @param soutceOptArray
     * @return
     * @throws Exception
     */
    ResponseData checkOperation(HttpServletRequest request, String opt, String[] sourceOptArray)throws Exception;

    AuthDataResponseDto getAuthData(Long userId);
}
