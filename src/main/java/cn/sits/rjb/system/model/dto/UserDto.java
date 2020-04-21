package cn.sits.rjb.system.model.dto;

import cn.sits.rjb.common.data.PageRequestDto;

/**
 * @author Neal.Sun
 * @version V1.0
 * @Package cn.sits.rjb.system.model.dto
 * @date 2020/04/21 14:45
 */
public class UserDto extends PageRequestDto {
    private Long userId;
    private Long deadTime;
    private String opt;

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDeadTime() {
        return deadTime;
    }

    public void setDeadTime(Long deadTime) {
        this.deadTime = deadTime;
    }
}
