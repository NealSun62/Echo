package cn.sits.rjb.common.utils;

import java.util.Calendar;

/**
 * @Description ID 生成工具 规则为 当前时间的值+三位随机数  组成的一个16位数字
 * @Author louis.lu
 * @Date 2018-07-09 17:34
 */
public class IdworkUtil {
    public static long  getId() {
        int randomNumber = (int) (Math.random() * (1000) + 1);//产生一个随机的三位数
        Calendar c = Calendar.getInstance();
        String currentTime = String.valueOf(c.getTimeInMillis());
        return Long.parseLong(currentTime + randomNumber);
    }

    public static void main(String[] args) {
        System.out.println(IdworkUtil.getId());
    }
}
