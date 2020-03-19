package cn.sits.rjb.police.service;

import cn.sits.rjb.police.model.dto.*;

import java.util.List;

/**
 * @author Neal.Sun
 * @version V1.0
 * @Package cn.sits.rjb.police.service.impl
 * @date 2020/02/25 16:01
 */
public interface IPoliceService {
    List getTrafficPoliceEventResponseDtoList(TrafficPoliceEventRequestDto trafficPoliceEventRequestDto) throws Exception;

    //获取辖区信息
    List getAdminJurisdiction() throws Exception;

    //当月事件事故统计
    TrafficPoliceReportingResponseDto getPoliceReporting(TrafficPoliceReportingRequestDto requestDto) throws Exception;

    //1.2.20	当日值班信息
    PoliceDutyResponseDto getPoliceDuty(PoliceDutyRequestDto requestDto) throws Exception;

    //1.2.19	警员、警车、对讲机在线统计
    RTPoliceOnlineResponseDto getRTPoliceOnline() throws Exception;
}
