package cn.mf.javalib;

import java.util.ArrayList;

/**
 * @author menggod
 */
public class MyClass {
    public static void main (String [] args) {
        System.out.println("hhhh");
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        list.add(list.indexOf("3")+1,"haha");
        System.out.println(list);
    }
}
