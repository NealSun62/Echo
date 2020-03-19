package cn.sits.rjb.police.model.dto;

import cn.sits.rjb.common.data.PageRequestDto;

/**
 * @author dxt
 * @version V1.0
 * @Package cn.sits.rjb.traffic.model.dto
 * @date 2020/02/18 13:40
 */
public class PoliceDutyRequestDto extends PageRequestDto {
    //开始时间
    private String startTime;
    //默认时间
    private String endTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
