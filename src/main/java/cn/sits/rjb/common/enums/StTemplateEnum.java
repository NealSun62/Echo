package cn.sits.rjb.common.enums;

import java.util.HashMap;
import java.util.Map;

public  final class StTemplateEnum {
    public static final Map<Long,String> WARNINGTYPE = new HashMap<>();
    static{
        WARNINGTYPE.put(1l,"离线");
        WARNINGTYPE.put(2l,"故障");
        WARNINGTYPE.put(3l,"超高");
        WARNINGTYPE.put(4l,"超低");
        WARNINGTYPE.put(5l,"跳变");
        WARNINGTYPE.put(6l,"连续不变");
    }

    public static final Map<Long,String> WARNINGWAY = new HashMap<>();
    static{
        WARNINGWAY.put(1l,"邮件");
        WARNINGWAY.put(2l,"短信");
        WARNINGWAY.put(3l,"App卡片推送");
        WARNINGWAY.put(4l,"站内消息");
    }

    public static final Map<Long,String> RECORDSTATE = new HashMap<>();
    static{
        RECORDSTATE.put(1l,"告警中");
        RECORDSTATE.put(2l,"人工解除");
        RECORDSTATE.put(3l,"系统解除");
        RECORDSTATE.put(4l,"误报");
    }

    public static final Map<Long,String> WarningGrade = new HashMap<>();
    static{
        WarningGrade.put(1l,"轻微");
        WarningGrade.put(2l,"普通");
        WarningGrade.put(3l,"严重");
    }
    public static final Map<Long,String> WarningState = new HashMap<>();
    static{
        WarningState.put(0l,"无响应");
        WarningState.put(1l,"正常响应");
        WarningState.put(2l,"未知");
    }
}
