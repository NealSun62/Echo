package cn.sits.rjb.police.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author Neal.Sun
 * @version V1.0
 * @Package cn.sits.rjb.traffic.model.po
 * @date 2020/02/18 13:28
 */
public class TrafficPoliceReportingDto {
    //事件ID
    private int eventId;
    //上报警员id
    private int reportPoliceId;
    //上报警员名称
    private String reportPoliceName;
    //事件类型
    private int eventType;
    //事件内容
    private String eventContent;
    //经度
    private int longitude;
    //纬度
    private int latitude;
    //事件图片路径
    private String picturePath;
    //上报时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date reportTime;
    //状态（1：有效；2：无效
    private int state;
    //状态修改时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date stateUpdateTime;
    //事件处置状态（1：上报、2：已接收、3：处理中、4处理完毕
    private int handleState;
    //处置警员ID
    private int handlePoliceId;
    //处置警员名称
    private String handlePoliceName;
    //高速段ID
    private int eventExpresswayId;
    //处置时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date handleTime;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEventExpresswayId() {
        return eventExpresswayId;
    }

    public void setEventExpresswayId(int eventExpresswayId) {
        this.eventExpresswayId = eventExpresswayId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }


    public int getReportPoliceId() {
        return reportPoliceId;
    }

    public void setReportPoliceId(int reportPoliceId) {
        this.reportPoliceId = reportPoliceId;
    }

    public String getReportPoliceName() {
        return reportPoliceName;
    }

    public void setReportPoliceName(String reportPoliceName) {
        this.reportPoliceName = reportPoliceName;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getEventContent() {
        return eventContent;
    }

    public void setEventContent(String eventContent) {
        this.eventContent = eventContent;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getStateUpdateTime() {
        return stateUpdateTime;
    }

    public void setStateUpdateTime(Date stateUpdateTime) {
        this.stateUpdateTime = stateUpdateTime;
    }

    public int getHandleState() {
        return handleState;
    }

    public void setHandleState(int handleState) {
        this.handleState = handleState;
    }

    public int getHandlePoliceId() {
        return handlePoliceId;
    }

    public void setHandlePoliceId(int handlePoliceId) {
        this.handlePoliceId = handlePoliceId;
    }

    public String getHandlePoliceName() {
        return handlePoliceName;
    }

    public void setHandlePoliceName(String handlePoliceName) {
        this.handlePoliceName = handlePoliceName;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }
}
