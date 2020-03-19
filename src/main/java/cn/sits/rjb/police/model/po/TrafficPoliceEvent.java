package cn.sits.rjb.police.model.po;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author Neal.Sun
 * @version V1.0
 * @Package cn.sits.rjb.traffic.model.po
 * @date 2020/02/18 13:28
 */
public class TrafficPoliceEvent {
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
    //事件坐标
    private String eventCoordinate;
    //事件图片路径
    private String picturePath;
    //上报时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date reportTime;
    //状态
    private int state;
    //状态修改时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifyStateTime;
    //事件处置状态
    private int eventHandleState;
    //处置警员ID
    private int handlePoliceId;
    //处置警员名称
    private String handlePoliceName;
    //处置时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date handleTime;

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

    public String getEventCoordinate() {
        return eventCoordinate;
    }

    public void setEventCoordinate(String eventCoordinate) {
        this.eventCoordinate = eventCoordinate;
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

    public Date getModifyStateTime() {
        return modifyStateTime;
    }

    public void setModifyStateTime(Date modifyStateTime) {
        this.modifyStateTime = modifyStateTime;
    }

    public int getEventHandleState() {
        return eventHandleState;
    }

    public void setEventHandleState(int eventHandleState) {
        this.eventHandleState = eventHandleState;
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
