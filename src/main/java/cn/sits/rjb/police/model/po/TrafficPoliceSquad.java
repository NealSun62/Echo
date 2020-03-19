package cn.sits.rjb.police.model.po;

import java.io.Serializable;
import lombok.Data;

/**
 * T_TRAFFIC_POLICE_SQUAD
 * @author 
 */
@Data
public class TrafficPoliceSquad implements Serializable {
    /**
     * 大队ID
     */
    private int squadId;

    /**
     * 大队名称
     */
    private String squadName;

    /**
     * 管辖区域ID
     */
    private int manageDiadrictId;

    /**
     * 大队电话
     */
    private String squadTel;

    /**
     * 描述
     */
    private String squadDesc;

    private static final long serialVersionUID = 1L;
}