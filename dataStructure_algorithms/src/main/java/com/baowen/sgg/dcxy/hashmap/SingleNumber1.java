package com.baowen.sgg.dcxy.hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 136
 * 只出现一次的数字
 * 数组中其他数字出现了2次，就一个数只出现了一次
 *
 * 最初的想法，存入map ，遍历2遍取出
 *
 * 老师的改进  利用contains遍历一遍 解决
 *
 * @author mangguodong
 * @create 2022-07-07
 */
public class SingleNumber1 {

    public static void main(String[] args) {
        int[] nums = {4,1,2,1,2};
        System.out.println(SingleNumber(nums));
    }

    /**
     * 数学方法异或
     *
     * a(+)a = 0
     * a(+)0 = a
     *
     * 又
     * a(+)a(+)b =b
     *
     * @param ints
     * @return
     */
    public static int SingleNumber(int[] ints){

        int result = 0;
        for (int anInt : ints) {
            result^=anInt;
        }

        return result;
    }

    /**
     *
     * 数学方法
     * 2*(a+b+c)-(a+b+b+c+c) = a
     *
     * 利用set求解
     *
     * @param ints
     * @return
     */
    public static int SingleNumber3(int[] ints){

        HashSet<Integer> set = new HashSet<>();
        int arraySum = 0;
        for (Integer anInt : ints) {
            set.add(anInt);
            arraySum+=anInt;
        }

        int setSum = 0;

        for (Integer integer : set) {
            setSum+=integer;
        }

        return setSum*2-arraySum;


    }


    /**
     * 利用map结构特性将时间复杂度降为了O(n)
     *
     * @param ints
     * @return
     */
    public static int SingleNumber2(int[] ints){

        HashMap<Integer, Integer> map = new HashMap<>();

        for (Integer anInt : ints) {
            if(map.get(anInt)!=null){
                map.remove(anInt);
            }else {
                map.put(anInt,1);
            }
        }

        return map.keySet().iterator().next();
    }

    /**
     *
     * arraylist 的 contains 对象 和 remove 对象都是遍历整个集合  即O(n)
     *
     * 所以算法复杂度是 O^2
     *
     * @param ints
     * @return
     */
    public static int SingleNumber1(int[] ints){
        ArrayList<Integer> list = new ArrayList<>();

        for (Integer anInt : ints) {
            if(list.contains(anInt)){
                list.remove(anInt);
            }else {
                list.add(anInt);
            }
        }

        return list.get(0);
    }






}
