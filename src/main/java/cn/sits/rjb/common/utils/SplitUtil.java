package cn.sits.rjb.common.utils;

//import org.apache.commons.collections.CollectionUtils;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 分割
 */
public class SplitUtil {

    //list为string类型
    public static String splitString(List<String> list){
        if(list!=null && list.size()>0){
            String sb = "(";
            for (int i = 0; i < list.size()-1; i++) {
                sb += "'";
                sb += list.get(i);
                sb += "'";
                sb += ",";
            }
            sb += "'";
            sb += list.get(list.size()-1);
            sb += "'";
            sb += ")";
            return sb;
        }
        return null;
    }

    public static String splitLong(List<Long> list){
        if(list!=null && list.size()>0){
            String sb = "(";
            for (int i = 0; i < list.size()-1; i++) {
                sb += list.get(i);
                sb += ",";
            }
            sb += list.get(list.size()-1);
            sb += ")";
            return sb;
        }
        return null;
    }
    public static String DealMsgRate(int num,int total){
        if (total != 0){
            float result = (float) num * 100 / total;
            DecimalFormat df = new DecimalFormat("0.00");
            String resultString = df.format(result);
            return ""+ resultString + "%";
        }
        return "—";
    }
}
