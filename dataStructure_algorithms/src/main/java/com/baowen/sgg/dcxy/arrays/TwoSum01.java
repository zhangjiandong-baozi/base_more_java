package com.baowen.sgg.dcxy.arrays;

import javax.lang.model.element.VariableElement;
import java.util.HashMap;

/**
 * @author mangguodong
 * @create 2022-05-06
 *
 * sgg大厂学院
 */
public class TwoSum01 {

    public static void main(String[] args) {

        int[] ints = {1, 3, 5, 7, 6};
//        for (int i : towSum01(ints, 11)) {
//            System.out.println("i = " + i);
//        }

        for (int i : twoSum03(ints, 11)) {
            System.out.println("i = " + i);
        }
    }

    /**
     * 暴力破解法
     * 时间复杂度是 n*(n-1)/2  即 O(n平方)
     * 为啥我会写这么多无用冗余的代码
     *
     * 对于数组的理解不够,数组的下标其实就是元素值的位置，而我确还用开辟新变量来存位置
     *
     * i+i1=target  ==     i1=target-i
     *
     * @param ints
     * @param target
     * @return
     */
    public static int[] towSum01(int[] ints,int target){
        // 13 57 6    == 11  ==>  2,4
        int[] result = new int[2];
        int startIndex = 0;
        int endIndex = 0;
        for (int i = 0; i < ints.length; i++) {
            int second = target-ints[i];
            System.out.println("second = " + second);
            for (int i1 = i+1; i1 < ints.length; i1++) {
                if(second==ints[i1]){
                    endIndex = i1;
                    result[0]=startIndex;
                    result[1]=endIndex;
                    break;
                }
            }
            ++startIndex;
        }

        if(0!=result[1]){
            return result;
        }else {
            throw new RuntimeException("数组内找不到相应的下标");
        }
    }

    //和自己写的区别？

    public static int[] twoSum02(int[] ints,int target){

        for (int i = 0; i < ints.length-1; i++) {
            for (int j = i+1; j < ints.length; j++) {
                if(ints[i]+ints[j] == target){
                    return new int[]{i,j};
                }
            }
        }
        throw new RuntimeException("数组内找不到符合的下标");
    }


    /**
     *
     * 使用hash表 空间换时间
     *空间复杂度是O(n) 、时间复杂度是2n  即 O(n)
     * 思路: 遍历数组存到哈希表里，key为数组的值，value为索引下标；在遍历数组 在map中取出 target-[i] 的值和下标,然后返回结果 i和map.get(target-[i])
     * @param ints
     * @param target
     * @return
     */
    public static int[] twoSum03(int[] ints,int target){
        //空间复杂度是O(n)
        HashMap<Integer, Integer> map = new HashMap<>();

        // target 10
        //1 2 3 7 8 9
        //时间复杂度是2n  即 O(n)
        for (int i = 0; i < ints.length; i++) {
            //1,0
            //9,5
            map.put(ints[i],i);
        }

        for (int i = 0; i < ints.length; i++) {
            //9 = key
            int thatNum = target-ints[i];

            //
            if(map.containsKey(thatNum) && map.get(thatNum) !=i){

                return  new int[]{i,map.get(thatNum)};
            }
        }

        throw new RuntimeException("数组内找不到符合的下标");
    }


    // 先去判断前面有没有对应相加得到target的值，有就输出,没有就把自己加入到map中
    // 时间复杂度是单个n
    public static int[] twoSum04(int[] ints,int target){
        //空间复杂度是O(n)
        HashMap<Integer, Integer> map = new HashMap<>();

        //时间复杂度O(n)
        for (int i = 0; i < ints.length; i++) {
            int thatNum = target-ints[i];
            if(map.containsKey(thatNum)){
                return  new int[]{map.get(thatNum),i};
            }
            map.put(ints[i],i);
        }

        throw new RuntimeException("数组内找不到符合的下标");
    }







}
