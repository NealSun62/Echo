package cn.sits.rjb.common.utils;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MathUtil {
    /**
     * 两数相除保留两位小数（54.33%）
     * @param num
     * @param total
     * @return
     */
    public static String DealMsgRate(int num,int total){
        if (total != 0){
            float result = (float) num * 100 / total;
            DecimalFormat df = new DecimalFormat("0.00");
            String resultString = df.format(result);
            return ""+ resultString + "%";
        }
        return "—";
    }

    /**
     * 两数相除保留两位小数（54%）
     * @param num
     * @param total
     * @return
     */
    public static String DealRateMsg(int num,int total){
        if (total != 0){
            float result = (float) num * 100 / total;
            DecimalFormat df = new DecimalFormat("0");
            String resultString = df.format(result);
            return ""+ resultString + "%";
        }
        return "—";
    }

    public static String DealGetRate(int num,int total,int decimal){
        if (total != 0){
            float result = (float) num * 100 / total;
            StringBuffer buffer = new StringBuffer();
            buffer.append("0");
            if (decimal > 0){
                buffer.append(".");
                for (int i=0;i<decimal;i++){
                    buffer.append("0");
                }
            }
            DecimalFormat df = new DecimalFormat(buffer.toString());
            String resultString = df.format(result);
            return ""+ resultString + "%";
        }
        return "—";
    }
    /**
     * 两数相除保留两位小数（0.23）
     * @param num
     * @param total
     * @return
     */
    public static String DealRate(int num,int total){
        if (total != 0){
            float result = (float) num  / total;
            DecimalFormat df = new DecimalFormat("0.00");
            String resultString = df.format(result);
            return resultString;
        }
        return "—";
    }

    public static List<Integer> sumList(List<Integer> list1,List<Integer> list2){
        List<Integer> result = IntStream.range(0, Math.min(list1.size(), list2.size()))
                .map(i -> list1.get(i) + list2.get(i))
                .boxed()
                .collect(Collectors.toList());
        return result;
    }

    public static List<Double> sumDoubleList(List<Double> list1,List<Double> list2){
        List<Double> result = IntStream.range(0, Math.min(list1.size(), list2.size()))
                .mapToDouble(i -> list1.get(i) + list2.get(i))
                .boxed()
                .collect(Collectors.toList());
        return result;
    }
}


