package com.baowen.base.jvm;

import java.util.UUID;

/**
 * @author mangguodong
 * @create 2021-04-06
 */
public class Base01_HeapParam {

    public static void main(String[] args) {


        System.out.println("电脑的核心数为:"+Runtime.getRuntime().availableProcessors());

        long maxMemory = Runtime.getRuntime().maxMemory() ;//返回 Java 虚拟机试图使用的最大内存量。
        long totalMemory = Runtime.getRuntime().totalMemory() ;//返回 Java 虚拟机中的内存总量(初始内存)。
        System.out.println("MAX_MEMORY = " + maxMemory + "（字节）、" + (maxMemory / (double)1024 / 1024) + "MB");
        System.out.println("TOTAL_MEMORY = " + totalMemory + "（字节）、" + (totalMemory / (double)1024 / 1024) + "MB");

        String str = "";
//        for (int i = 0; i < 100000; i++) {
//            str = str+ UUID.randomUUID().toString().substring(0,16);
//        }


        while(true){
            str = str+ UUID.randomUUID().toString().substring(0,16);
        }


    }
}
