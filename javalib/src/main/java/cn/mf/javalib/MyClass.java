package cn.mf.javalib;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author menggod
 */
public class MyClass {
    public static void main (String [] args) {
      //  System.out.println("hhhh");
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        list.add(list.indexOf("3")+1,"haha");
       // System.out.println(list);
        System.out.println(formatHH_mmBySecond(355));
    }

    public static String formatHH_mmBySecond(long seconds) {
        try {
            Date date = new Date(seconds * 1000);
            SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
            return sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
