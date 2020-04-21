package cn.sits.rjb.system.model.dto;


import cn.sits.rjb.system.model.po.SysAuth;

import java.util.List;

/**
 * Created by bf on 2018/12/10.
 */
public class AuthDataResponseDto {
    // 操作权限集合
    private List<SysAuth> authList;
    // 匝道ID集合
    private List<Long> rampIdList;

    public List<SysAuth> getAuthList() {
        return authList;
    }

    public void setAuthList(List<SysAuth> authList) {
        this.authList = authList;
    }

    public List<Long> getRampIdList() {
        return rampIdList;
    }

    public void setRampIdList(List<Long> rampIdList) {
        this.rampIdList = rampIdList;
    }
}
