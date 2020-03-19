package cn.sits.rjb.system.model.vo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SysUserVo {

    private Long sysUserId;

    private String userCode;//用户编码 也就是登录名

    private String userName;//用户名称

    private String passWord;//密码

    private String userImg;//头像

    private String userDuty;//职务

    private Integer userDistrictLevel;//等级

    private Long sysOrganizeId;//单位id

    private String organizeName;//单位名称

    private String userMobile;//用户手机

    private String areaCode;//行政区划

    private String userTel;//联系电话

    private String idCard;//身份证

    private String userEmail;//邮箱

    private int active;//是否有效

    private int registActive;//是否激活

    private int approve;// 0办公室人员 1办公室领导 8副局长 9局长

    private Integer loginCount;//登录次数

    private Integer userOrderBy;//用户排序

    private Date registActiveTime;//用户激活时间

    private Date dataChangeCreateTime;//创建时间

    private Date dataChangeLastTime;//最后修改时间

    private Date dataLoginLastTime;//最后登录时间

    private Integer waterCompany; //所属队伍

    private int dutyYype;//是否参与值班

    public Long getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Long sysUserId) {
        this.sysUserId = sysUserId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getUserDuty() {
        return userDuty;
    }

    public void setUserDuty(String userDuty) {
        this.userDuty = userDuty;
    }

    public Integer getUserDistrictLevel() {
        return userDistrictLevel;
    }

    public void setUserDistrictLevel(Integer userDistrictLevel) {
        this.userDistrictLevel = userDistrictLevel;
    }

    public Long getSysOrganizeId() {
        return sysOrganizeId;
    }

    public void setSysOrganizeId(Long sysOrganizeId) {
        this.sysOrganizeId = sysOrganizeId;
    }

    public String getOrganizeName() {
        return organizeName;
    }

    public void setOrganizeName(String organizeName) {
        this.organizeName = organizeName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getRegistActive() {
        return registActive;
    }

    public void setRegistActive(int registActive) {
        this.registActive = registActive;
    }

    public int getApprove() {
        return approve;
    }

    public void setApprove(int approve) {
        this.approve = approve;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public Integer getUserOrderBy() {
        return userOrderBy;
    }

    public void setUserOrderBy(Integer userOrderBy) {
        this.userOrderBy = userOrderBy;
    }

    public Date getRegistActiveTime() {
        return registActiveTime;
    }

    public void setRegistActiveTime(Date registActiveTime) {
        this.registActiveTime = registActiveTime;
    }

    public Date getDataChangeCreateTime() {
        return dataChangeCreateTime;
    }

    public void setDataChangeCreateTime(Date dataChangeCreateTime) {
        this.dataChangeCreateTime = dataChangeCreateTime;
    }

    public Date getDataChangeLastTime() {
        return dataChangeLastTime;
    }

    public void setDataChangeLastTime(Date dataChangeLastTime) {
        this.dataChangeLastTime = dataChangeLastTime;
    }

    public Date getDataLoginLastTime() {
        return dataLoginLastTime;
    }

    public void setDataLoginLastTime(Date dataLoginLastTime) {
        this.dataLoginLastTime = dataLoginLastTime;
    }

    public Integer getWaterCompany() {
        return waterCompany;
    }

    public void setWaterCompany(Integer waterCompany) {
        this.waterCompany = waterCompany;
    }

    public int getDutyYype() {
        return dutyYype;
    }

    public void setDutyYype(int dutyYype) {
        this.dutyYype = dutyYype;
    }
}
