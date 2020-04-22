package cn.sits.rjb.system.model.dto;


import cn.sits.rjb.system.model.po.SysAuth;

import java.util.List;

/**
 * Created by bf on 2018/12/10.
 */
public class AuthDataResponseDto {
    // 操作权限集合
    private List<SysAuth> authList;

    public List<SysAuth> getAuthList() {
        return authList;
    }

    public void setAuthList(List<SysAuth> authList) {
        this.authList = authList;
    }

}
