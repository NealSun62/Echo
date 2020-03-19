package cn.sits.rjb.police.model.po;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * T_TRAFFIC_POLICE_CAR
 * @author 
 */
@Data
public class TrafficPoliceCar implements Serializable {
    /**
     * 警车ID
     */
    private int policeCarId;

    /**
     * 车牌
     */
    private String licensePlate;

    /**
     * 车牌颜色
     */
    private Short plateColor;

    /**
     * 车型（1：2厢；2:3厢）
     */
    private Short carType;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 出厂日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date produceDate;

    private static final long serialVersionUID = 1L;
}