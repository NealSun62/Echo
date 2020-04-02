package cn.sits.rjb.system.mapper;

import cn.sits.rjb.system.model.dto.LoginUserResponseDto;
import cn.sits.rjb.system.model.dto.LoginUserResquestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<LoginUserResponseDto> findByLoginName(LoginUserResquestDto resquestDto);
}
