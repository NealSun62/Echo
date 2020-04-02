package cn.sits.rjb.system.model.dto;
import java.io.Serializable;

/**
 * 用户信息表
 */
public class LoginUserResquestDto implements Serializable {
    private String loginName;
    private String loginPassword;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
}