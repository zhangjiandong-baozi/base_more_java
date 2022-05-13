package com.baowen.sgg.dcxy.arrays;

import java.util.*;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。

 * 示例 1：
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 *
 * @author mangguodong
 * @create 2022-05-13
 */
public class ThreeSum02 {

    public static void main(String[] args) {
        int[] ints = {-1, 0, 1, 2, -1, -4};
       //System.out.println("threeSum(ints) = " + threeSum(ints));
        System.out.println("threeSum(ints) = " + threeSumByLR(ints));
    }


    /**
     * 双指针(左右指针求解三数之和)求解
     * 思路：（模拟现实生活中  3人组队打篮球，平均身高180）
     *
     * 先将数组排序，
     * 遍历数组，
     * 以最小的矮子为核心，矮子的右边为左指针，数组的最大位置为右指针，
     * 如果当前矮子>0 退出首次循环（因为三数之和只可能大于0）
     * 如果当前矮子和上一个数一样则跳过（因为上一次  把所有情况匹配结果项>=当前  且相同的匹配项是重复的 ，跳过 就去重了）
     *      当左指针小于右指针时
     *      满足条件，把3数存在list中
     *      当3数之和小于0时，左指针右移动同时向中间移动
     *          接下来 如果左指针位置小于右指针 且左指针+1 和当前左指针相同  ，左指针右移
     *          接下来 如果左指针位置小于右指针 且左指针-1 和当前右指针相同  ，右指针右移
     *      当3数之和大于0时，右指针左移
     *      当3数之和小于0时，左指针右移
     *
     * @param ints
     * @return
     */
    public static List<List<Integer>> threeSumByLR(int[] ints) {

        Arrays.sort(ints);
        ArrayList<List<Integer>> result = new ArrayList<>();
        int len = ints.length;
        for (int i = 0; i < len; i++) {

            if(ints[i]>0){
                break;
            }

            if (i>0 && ints[i]==ints[i-1]){
                continue;
            }

            int lp = i+1;
            int rp = len-1;

            while(lp<rp){
                int sum = ints[i]+ints[lp]+ints[rp];
                if(sum==0){

                    result.add(Arrays.asList(ints[i],ints[lp],ints[rp]));

                    lp++;
                    rp--;

                    while (lp<rp && ints[lp]==ints[lp-1]){
                        lp++;
                    }
                    while (lp<rp && ints[rp]==ints[rp+1]){
                        rp--;
                    }
                }

                if(sum<0){
                    lp++;
                }
                if(sum>0){
                    rp--;
                }
            }

        }

        return result;
    }



    // 暴力法
    // 写个三元组对象 ，重写 eq方法  即 eq方法是  三个数排序后是否还相等，相等就 一致，否则 就是 不同的
    // 三层循环求解   符合三数之和为0 就写入map中
    public static Set<ThreeTuple> threeSum(int[] ints){

        int length = ints.length;
        HashSet<ThreeTuple> result = new HashSet<>();
        for (int i = 0; i < ints.length-2; i++) {
            for (int j = i+1; j < ints.length-2; j++) {
                for (int k = j+1; k < ints.length; k++) {
                    if(ints[i]+ints[j]+ints[k]==0){
                        result.add(new ThreeTuple(ints[i],ints[j],ints[k]));
                    }
                }
            }
        }
        return result;
    }
}

class ThreeTuple{

   Integer i1;
   Integer i2;
   Integer i3;

    public ThreeTuple(Integer i1, Integer i2, Integer i3) {
        this.i1 = i1;
        this.i2 = i2;
        this.i3 = i3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThreeTuple that = (ThreeTuple) o;
        int[] thatInt = new int[]{that.i1,that.i2,that.i3};
        int[] thisInt = new int[]{i1,i2,i3};
        Arrays.sort(thatInt);
        Arrays.sort(thisInt);
        return thisInt[0]==thatInt[0] &&
               thisInt[1]==thatInt[1] &&
               thisInt[2]==thatInt[2] ;

    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return "ThreeTuple{" +
                "i1=" + i1 +
                ", i2=" + i2 +
                ", i3=" + i3 +
                '}';
    }
}
