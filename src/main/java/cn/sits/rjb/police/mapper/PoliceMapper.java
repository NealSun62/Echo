package cn.sits.rjb.police.mapper;


import cn.sits.rjb.police.model.dto.*;
import cn.sits.rjb.police.model.po.PoliceDuty;
import cn.sits.rjb.police.model.po.TrafficPoliceReporting;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Neal.Sun
 * @version V1.0
 * @Package cn.sits.rjb.traffic.mapper
 * @date 2020/02/18 15:17
 */
@Mapper
public interface PoliceMapper {
    List getTrafficPoliceEventList(TrafficPoliceEventRequestDto trafficPoliceEventRequestDto);

    //获取辖区信息
    List getAdminJurisdiction();

    List<TrafficPoliceReportingDto> getPoliceReporting(TrafficPoliceReportingRequestDto requestDto);

    List<PoliceDuty> getPoliceDutyList(PoliceDutyRequestDto requestDto);

    RTPoliceOnlineResponseDto getRTPoliceOnline();

    int getYesterdayPoliceReportingNumber(TrafficPoliceReportingRequestDto requestDto);
}
