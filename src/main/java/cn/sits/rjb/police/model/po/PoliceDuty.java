package cn.sits.rjb.police.model.po;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author dxt
 * @version V1.0
 * @Package cn.sits.rjb.traffic.model.pos
 * @date 2020/02/25 16:33
 */
public class PoliceDuty {
    //自增ID
    private int incrementId;
    //警员id
    private int policeId;
    //所配警车id
    private int policeCarId;
    //日期
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dutyDate;
    //值班开始时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date beginTime;
    //值班结束时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
    //管理辖区
    private int jurisdictionId;
    //所在岗点
    private String dutyPosition;
    //值班任务
    private String task;
    //类型（1早班，2晚班）
    private int dutyType;
    //姓名
    private String name;
    //性别
    private int sex;
    //出生年月
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date birthDate;
    //警衔
    private int policeRank;
    //所属中队ID
    private int squadId;
    //中队名称
    private String squadName;
    //辖区名称
    private String jurisdictionName;
    //辖区描述
    private String descript;

    public int getIncrementId() {
        return incrementId;
    }

    public void setIncrementId(int incrementId) {
        this.incrementId = incrementId;
    }

    public int getPoliceId() {
        return policeId;
    }

    public void setPoliceId(int policeId) {
        this.policeId = policeId;
    }

    public int getPoliceCarId() {
        return policeCarId;
    }

    public void setPoliceCarId(int policeCarId) {
        this.policeCarId = policeCarId;
    }

    public Date getDutyDate() {
        return dutyDate;
    }

    public void setDutyDate(Date dutyDate) {
        this.dutyDate = dutyDate;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getJurisdictionId() {
        return jurisdictionId;
    }

    public void setJurisdictionId(int jurisdictionId) {
        this.jurisdictionId = jurisdictionId;
    }

    public String getDutyPosition() {
        return dutyPosition;
    }

    public void setDutyPosition(String dutyPosition) {
        this.dutyPosition = dutyPosition;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getDutyType() {
        return dutyType;
    }

    public void setDutyType(int dutyType) {
        this.dutyType = dutyType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getPoliceRank() {
        return policeRank;
    }

    public void setPoliceRank(int policeRank) {
        this.policeRank = policeRank;
    }

    public int getSquadId() {
        return squadId;
    }

    public void setSquadId(int squadId) {
        this.squadId = squadId;
    }

    public String getJurisdictionName() {
        return jurisdictionName;
    }

    public void setJurisdictionName(String jurisdictionName) {
        this.jurisdictionName = jurisdictionName;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getSquadName() {
        return squadName;
    }

    public void setSquadName(String squadName) {
        this.squadName = squadName;
    }
}
