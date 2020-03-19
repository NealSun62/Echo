package cn.sits.rjb.police.model.vo;


import cn.sits.rjb.police.model.dto.TrafficPoliceEventResponseDto;

import java.util.List;

/**
 * @author Neal.Sun
 * @version V1.0
 * @Package cn.sits.rjb.traffic.model.po
 * @date 2020/02/18 13:28
 */
public class TrafficPoliceEventVo {
    private List<TrafficPoliceEventResponseDto> trafficPoliceEventList;

    public List<TrafficPoliceEventResponseDto> getTrafficPoliceEventList() {
        return trafficPoliceEventList;
    }

    public void setTrafficPoliceEventList(List<TrafficPoliceEventResponseDto> trafficPoliceEventList) {
        this.trafficPoliceEventList = trafficPoliceEventList;
    }
}
