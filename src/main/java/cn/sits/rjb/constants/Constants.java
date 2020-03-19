package cn.sits.rjb.constants;

import java.util.HashMap;
import java.util.Map;

public final class Constants {

    public static final int oneNum = 1;
    public static final int twoNum = 2;
    public static final int threeNum = 3;
    public static final int fourNum = 4;
    public static final int fiveNum = 5;

    public static final int TIME_LENGTH = 19;
    //状态
    public static final int BREAK = 0;//断开
    public static final int CONNENT = 1;//连接


    //资产类型
    public static final int JG = 1;//机柜
    public static final int FWQ = 2;//服务器
    public static final int MJ = 3;//门架
    public static final int SXJ = 4;//摄像机
    public static final int BD = 5;//北斗
    public static final int GKJ = 6;//工控机
    public static final int CDKZ = 7;//车道控制器
    public static final int CPSB = 8;//车牌识别器
    public static final int RSU = 9;//RSU 控制主机
    public static final int JHJ = 10;//交换机
    public static final int CJQ = 11;//车检器
    public static final int PASM = 14;//PASM识别卡
    public static final int RSUTXT = 15;//RSU天线头--路测单元

    /** 分页相关 */
    /**
     * 默认页数
     */
    public static final int DEFAULT_PAGE_INDEX = 0;
    /**
     * 默认每页显示条数
     */
    public static final int DEFAULT_PAGE_SIZE = 10;
    /**
     * 默认每页显示条数
     */
    public static final int DEFAULT_EXPORT_SIZE = 1000;


    public static final Map<Long, String> CATEGORY = new HashMap<>();

    static {
        CATEGORY.put(1l, "河流");
        CATEGORY.put(2l, "湖泊");
        CATEGORY.put(3l, "水库");
        CATEGORY.put(4l, "河段");
    }

    // 行政区划等级 省（直辖市，自治区，特别行区），市（地区 盟），县（区，市），乡（镇，街道办事处），村（社区）
    public static final Map<Integer, String> AREALEVEL = new HashMap<>();

    static {
        AREALEVEL.put(2, "市级");
        AREALEVEL.put(3, "区级");
        AREALEVEL.put(4, "乡级");
        AREALEVEL.put(5, "村级");
    }


    public static final Map<Long, String> TIME = new HashMap<>();

    static {
        TIME.put(1l, "年");
        TIME.put(2l, "月");
        TIME.put(3l, "周");
        TIME.put(4l, "日");
    }


    public static final Map<Integer, String> COMPARE = new HashMap<>();

    static {
        COMPARE.put(0, "=");
        COMPARE.put(1, ">");
        COMPARE.put(2, "<");
    }

    public static final Map<Long, String> PATROLPLANTYPE = new HashMap<>();

    static {
        PATROLPLANTYPE.put(1l, "汛期");
        PATROLPLANTYPE.put(2l, "非汛期");
    }

    public static final Map<String, String> FIlETYPE = new HashMap<>();

    static {
        //图片
        FIlETYPE.put(".bmp", "picture");
        FIlETYPE.put(".png", "picture");
        FIlETYPE.put(".jpg", "picture");
        FIlETYPE.put(".jpeg", "picture");
        FIlETYPE.put(".gif", "picture");
        //表格
        FIlETYPE.put(".xls", "excel");
        FIlETYPE.put(".xlsx", "excel");
        //app版本
        FIlETYPE.put(".apk", "app");
        //word
        FIlETYPE.put(".doc", "word");
        FIlETYPE.put(".docx", "word");
    }

}
