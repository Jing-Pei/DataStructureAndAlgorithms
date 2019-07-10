package com.ppc.shunxu;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ppc
 * @create 2019-05-21-16:09
 */
public class ElemTypeMain {
    public static void main(String[] args) throws Exception{
        ElemType elemType = new ElemType(5);
        elemType.add("甲");
        elemType.add("乙");
        elemType.add("丙");
        elemType.add("丁");
        elemType.add(2,"戊");
        System.out.println(elemType);
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
        Integer intervalDays = getIntervalDays(f.parse("20180709000000"), f.parse("20190522000000"));
        System.out.println(intervalDays);
        System.out.print(intervalDays.doubleValue()/365 * 5);
        //elemType.add("饱满");
    }

    public static int getIntervalDays(Date fDate, Date oDate) {

        if (null == fDate || null == oDate) {

            return -1;
        }

        long intervalMilli = oDate.getTime() - fDate.getTime();

        return (int) (intervalMilli / (1000 * 60 * 60 * 24));

    }
}
