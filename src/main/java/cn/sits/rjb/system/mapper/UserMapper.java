package cn.sits.rjb.system.mapper;

import cn.sits.rjb.system.model.po.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    SysUser findByUserCodeAndActive(@Param("userCode")String userCode);
}
