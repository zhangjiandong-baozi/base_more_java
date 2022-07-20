package com.baowen.sgg.dcxy.hashmap6;

import java.util.Arrays;
import java.util.HashSet;

/**
 *
 * 128 最长连续序列长度
 *
 *
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 *
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * 示例 1：
 *
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-consecutive-sequence
 * @author mangguodong
 * @create 2022-07-08
 */
public class LongestConsecutiveSequence2 {
    public static void main(String[] args) {
        int[] ints = new int[]{1, 8, 10, 11, 12, 13,18,21,22,23,24,25,26,37};
        int[] nums2 = {0,3,7,2,5,8,4,6,0,1};

        System.out.println(LongestConsecutiveSequence(nums2));
    }

    /**
     * 暴力法
     * 优化LongestConsecutiveSequence3
     * 思路:  内层循环其实做了很多无用功，即  1234 遍历了了4次的到4  到2时  遍历了2 得到了3  其实 2是无效遍历
     *        即连续序列中只要不是 起始位置的数字的遍历都是 无效遍历
     *        即只对起始位置遍历
     *        即这个数字的 a-1 存在  于 set中 我就不遍历
     *
     * 算法复杂度：算法复杂度为O(n)
     *
     *      外层for循环 + 内层while 循环  正好完整的 遍历了整个数组
     *      即外层for循环 不是首位数字的跳过， 内层while 循环 则是遍历 不是首 位数字的 所有数字
     *
     * @param ints
     * @return
     */
    public static int LongestConsecutiveSequence(int[] ints){
        if(ints.length==0) return 0;

        int len = ints.length;
        int maxlen = 1;

        //将所以数字放入set中
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < ints.length; i++) {
            set.add(ints[i]);
        }
        //遍历整个数组
        for (int i = 0; i < len; i++) {
            //找到此数子的连续序列的最大长度
            int curr = ints[i];
            int length = 0;
            //只遍历起始位置的数字
            if(!set.contains(curr-1)){
                while(set.contains(curr)){
                    curr++;
                    length++;
                }
            }
            maxlen = maxlen>=length?maxlen:length;
        }
        return maxlen;

    }

    /**
     * 暴力法
     * 优化LongestConsecutiveSequence2
     * 优化contains方法
     *
     *算法复杂度： 外层for n 计算连续数字长度  n  contains 方法O(1)  即 O(n^2)
     *
     * @param ints
     * @return
     */
    public static int LongestConsecutiveSequence3(int[] ints){
        if(ints.length==0) return 0;

        int len = ints.length;
        int maxlen = 1;

        //将所以数字放入set中
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < ints.length; i++) {
            set.add(ints[i]);
        }

        //遍历整个数组
        for (int i = 0; i < len; i++) {
            //找到此数子的连续序列的最大长度
            int curr = ints[i];
            int length = 0;
            while(set.contains(curr)){
                curr++;
                length++;
            }
            maxlen = maxlen>=length?maxlen:length;
        }
        return maxlen;
    }


    /**
     * 暴力法很重要；暴力法的优化可以把时间复杂度降低
     *
     * 思路：
     *      遍历数组，找到某个数字接下来的连续数字,并返回长度
     *
     * 时间复杂度： 外层for 一次 n contains 一次n   计算连续数字长度 一次n  即  O(n^3)
     *
     *
     * @param ints
     * @return
     */
    public static int LongestConsecutiveSequence2(int[] ints){

        if(ints.length==0) return 0;

        int len = ints.length;
        int maxlen = 1;

        //遍历整个数组
        for (int i = 0; i < len; i++) {
            //找到此数子的连续序列的最大长度
            int curr = ints[i];
            int length = 0;
            while(contains(ints,curr)){
                curr++;
                length++;
            }
            maxlen = maxlen>=length?maxlen:length;
        }


        return maxlen;
    }

    private static boolean contains(int[] ints, int t) {

        for (int i = 0; i< ints.length; i++) {
            if(ints[i] == t){
                return true;
            }
        }
        return false;
    }


    /**
     *
     * 思路： 将数组排序，如果后一位比前一位大于2 返回前一位索引+1
     * 好像不对   假如 是 1 8 10 11 12 13 呢
     *
     * ==>
     *
     * 排序后 使用左右指针
     *       遍历整个数组   end 遍历
     *      如果后一个数比前一个数 大1  start 变成end -1 ，然后end 继续后移，不满足条件 返回 长度
     *      然后保留最大的长度
     *
     * @param ints
     * @return
     */
    public static int LongestConsecutiveSequence1(int[] ints){

        if(ints.length==0) return 0;
        if(ints.length==1) return 1;
        Arrays.sort(ints);

        int maxlen =1;
        int start = 0,end =1;
        int len = ints.length;
        int count = 1;
        while (end<len){

            System.out.println("start ="+start + " end = "+end);
            if(ints[start]+count == ints[end]&& (maxlen ==1 ||maxlen< end-start+1) && start<end){
                System.out.println("已经连续 start = "+start +" count =" +count + " end ="+end);
                maxlen = end-start+1;
                System.out.println("maxlen" +maxlen);
                count++;
                start--;
            }
            end++;
            start++;


        }


        return maxlen;
    }

}
