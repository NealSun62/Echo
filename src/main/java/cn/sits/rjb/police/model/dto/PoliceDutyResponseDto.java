package cn.sits.rjb.police.model.dto;

import cn.sits.rjb.common.data.PageResponseDto;
import cn.sits.rjb.police.model.po.PoliceDuty;
import cn.sits.rjb.traffic.model.po.RegionKeyVehicle;

import java.util.List;

/**
 * @author dxt
 * @version V1.0
 * @Package cn.sits.rjb.traffic.model.dto
 * @date 2020/02/24 16:10
 */
public class PoliceDutyResponseDto extends PageResponseDto {
    private List<PoliceDuty> policeDutyList;

    //早班人数
    private Integer dayShiftCount;

    //晚班人数
    private Integer nightShiftCount;

    public List<PoliceDuty> getPoliceDutyList() {
        return policeDutyList;
    }

    public void setPoliceDutyList(List<PoliceDuty> policeDutyList) {
        this.policeDutyList = policeDutyList;
    }

    public Integer getDayShiftCount() {
        return dayShiftCount;
    }

    public void setDayShiftCount(Integer dayShiftCount) {
        this.dayShiftCount = dayShiftCount;
    }

    public Integer getNightShiftCount() {
        return nightShiftCount;
    }

    public void setNightShiftCount(Integer nightShiftCount) {
        this.nightShiftCount = nightShiftCount;
    }
}
