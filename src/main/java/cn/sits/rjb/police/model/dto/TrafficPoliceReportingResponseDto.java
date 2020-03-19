package cn.sits.rjb.police.model.dto;

import cn.sits.rjb.common.data.PageResponseDto;
import cn.sits.rjb.police.model.po.ReportType;
import cn.sits.rjb.police.model.po.TrafficPoliceReporting;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Neal.Sun
 * @version V1.0
 * @Package cn.sits.rjb.traffic.model.dto
 * @date 2020/02/18 13:40
 */
public class TrafficPoliceReportingResponseDto extends PageResponseDto {
    private List<TrafficPoliceReportingDto> trafficPoliceReportingList;
    private int yesterdayReportNumber;
    private int resolvedReportNumber;
    private int expresswayCount;
    private int reportTypeCount;
    private List<ReportType> reportTypeList = new ArrayList<ReportType>();
    private List<ExpresswayDto> expresswayList = new ArrayList<ExpresswayDto>();

    public List<ExpresswayDto> getExpresswayList() {
        return expresswayList;
    }

    public void setExpresswayList(List<ExpresswayDto> expresswayList) {
        this.expresswayList = expresswayList;
    }

    public int getYesterdayReportNumber() {
        return yesterdayReportNumber;
    }

    public void setYesterdayReportNumber(int yesterdayReportNumber) {
        this.yesterdayReportNumber = yesterdayReportNumber;
    }

    public int getExpresswayCount() {
        return expresswayCount;
    }

    public void setExpresswayCount(int expresswayCount) {
        this.expresswayCount = expresswayCount;
    }

    public int getReportTypeCount() {
        return reportTypeCount;
    }

    public void setReportTypeCount(int reportTypeCount) {
        this.reportTypeCount = reportTypeCount;
    }

    public List<ReportType> getReportTypeList() {
        return reportTypeList;
    }

    public void setReportTypeList(List<ReportType> reportTypeList) {
        this.reportTypeList = reportTypeList;
    }

    public int getResolvedReportNumber() {
        return resolvedReportNumber;
    }

    public void setResolvedReportNumber(int resolvedReportNumber) {
        this.resolvedReportNumber = resolvedReportNumber;
    }

    public List<TrafficPoliceReportingDto> getTrafficPoliceReportingList() {
        return trafficPoliceReportingList;
    }

    public void setTrafficPoliceReportingList(List<TrafficPoliceReportingDto> trafficPoliceReportingList) {
        this.trafficPoliceReportingList = trafficPoliceReportingList;
    }
}
