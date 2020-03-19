package cn.sits.rjb.police.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author dxt
 * @version V1.0
 * @Package cn.sits.rjb.traffic.model.dto
 * @date 2020/02/24 16:10
 */
public class RTPoliceOnlineResponseDto {
    //自增ID
    private Integer incrementId;
    //警察在线数量
    private Integer policeNumber;
    //警车在线数量
    private Integer policeCarNumber;
    //对讲机在线数量
    private Integer intercomNumber;
    //写入时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    public Integer getIncrementId() {
        return incrementId;
    }

    public void setIncrementId(Integer incrementId) {
        this.incrementId = incrementId;
    }

    public Integer getPoliceNumber() {
        return policeNumber;
    }

    public void setPoliceNumber(Integer policeNumber) {
        this.policeNumber = policeNumber;
    }

    public Integer getPoliceCarNumber() {
        return policeCarNumber;
    }

    public void setPoliceCarNumber(Integer policeCarNumber) {
        this.policeCarNumber = policeCarNumber;
    }

    public Integer getIntercomNumber() {
        return intercomNumber;
    }

    public void setIntercomNumber(Integer intercomNumber) {
        this.intercomNumber = intercomNumber;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
