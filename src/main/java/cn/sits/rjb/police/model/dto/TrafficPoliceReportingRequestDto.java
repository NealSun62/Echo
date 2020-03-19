package cn.sits.rjb.police.model.dto;

import cn.sits.rjb.common.data.PageRequestDto;

/**
 * @author Neal.Sun
 * @version V1.0
 * @Package cn.sits.rjb.traffic.model.dto
 * @date 2020/02/18 13:40
 */
public class TrafficPoliceReportingRequestDto extends PageRequestDto {
    private String startTime;
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
