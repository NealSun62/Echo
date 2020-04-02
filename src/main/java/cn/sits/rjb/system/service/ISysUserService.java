package cn.sits.rjb.system.service;

import cn.sits.rjb.common.data.ResponseData;
import cn.sits.rjb.system.model.dto.LoginUserResponseDto;
import cn.sits.rjb.system.model.dto.LoginUserResquestDto;

import java.util.List;

/**
 * @author Neal.Sun
 * @version V1.0
 * @Package cn.sits.rjb.system.service
 * @date 2020/02/13 16:46
 */
public interface ISysUserService {
    List<LoginUserResponseDto> findByLoginName(LoginUserResquestDto resquestDto) throws Exception;

    ResponseData login(String userName, String password) throws Exception;
}
