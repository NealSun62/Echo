package cn.sits.rjb.common.utils;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjunwei
 * @version 1.0
 * @date 2019-12-09 10:00
 */
public class CastEntityUtil {
    //转换实体类
    public static <T> List<T> objectToBean(List<Object[]> objList, Class<T> clz) throws Exception {
        List<T> returnList = new ArrayList<T>();
        Object[] co = objList.get(0);
        Class[] c2 = new Class[co.length];

        //确定构造方法
        for (int i = 0; i < co.length; i++) {
            c2[i] = co[i].getClass();
        }

        for (Object[] o : objList) {
            Constructor<T> constructor = clz.getConstructor(c2);
            returnList.add(constructor.newInstance(o));
        }
        return returnList;
    }
}
