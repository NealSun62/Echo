package cn.sits.rjb.police.model.po;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * T_TRAFFIC_POLICE
 * @author dxt
 */
@Data
public class TrafficPolice implements Serializable {
    /**
     * 警员ID
     */
    private int policeId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private Short sex;

    /**
     * 出生年月
     */
    private String birthDate;

    /**
     * 警衔
     */
    private Short policeRank;

    /**
     * 所属大队ID
     */
    private int squadId;

    private static final long serialVersionUID = 1L;
}