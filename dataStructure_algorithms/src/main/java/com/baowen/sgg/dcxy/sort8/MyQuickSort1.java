package com.baowen.sgg.dcxy.sort8;

import java.util.Arrays;

/**
 * @author mangguodong
 * @create 2022-07-26
 */
public class MyQuickSort1 {

    public static void main(String[] args) {
        int[] nums = {3, 45, 78, 36, 52, 11, 39, 36, 52};
        int[] nums1 = {6,7,3,4};

        quickSort(nums1,0,nums1.length-1);
        printInts(nums1);
    }

    /**
     * 思路:
     *      找到一个基准数分区， 它左边数都比它小，它右边的数都比它大
     *          接下来，在上个左右分区继续找到这个基准数递归操作
     *          直到只剩下自己
     *
     * 涉及都递归，子递归也需要知道 左右分区的起始位置
     *
     * @param ints
     */
    public  static void quickSort(int[] ints,int start,int end){

        // 小问题： 只剩下一个元素  或者 2个相同的元素 时弹出
        if(start>=end) return;

        //小问题: 对于给定的数组，需要数组进行分区内排序且返回基准数
        int index = partiton1(ints,start,end);

        //对左边分区进行同样的分区排序操作
        quickSort(ints,start,index-1);
        quickSort(ints,index+1,end);


    }

    /**
     * 思路:
     *   空位思想 （） 可以是另外一种角度看 ，是老师的讲解角度
     *
     *   假定第一个数为空位，用临时变量保存空位的值
     *      当左指针小于右指针 时
     *          在左移右指针的同时，比空位大的值保持不动且左移右指针 ,比空位小的值换到左指针位置__(可以看成把空位从最左边移到最右边了);
     *          在移动左指针，比空位小的值不动且右移左指针，比空位大的值换到右指针位置__(可以看成把空位从最右边移到最左边了);
     *
     *       即左右指针相遇之前, 比空位小的数都到了左边，比空位大的数都到了右边__(空位停止的时候，左边的数比空位小，右边的数比空位大)。
     *
     *
     * @param ints
     * @param start
     * @param end
     * @return
     */
    private static int partition(int[] ints, int start, int end) {
        int pivot = ints[start];
        int left = start,right = end;

        while(left<right){
            while (left<right && ints[right]>=pivot){
                //右边的值大于空位，右指针左移
                right--;
            }
            // 当右边的值小于空位时，交换 左右指针元素的值 ；保证右边的值都大于 空位
            ints[left] = ints[right];

            while (left<right && ints[left]<=pivot){
                //左边的值小于空位，左指针右移
                left++;
            }
            //当左边的值大于空位时,交换左右指针元素的值；保证左边的值小于空位
            ints[right] = ints[left];
        }
        //退出循环，即左右指针碰面，即此位置左边的元素 都比 空位小，右边的位置都比空位大
        ints[left] = pivot;

        return left;

    }

    public static int partiton1(int[] ints,int start,int end){
        int pivot = ints[start];
        int left = start,right = end;

        while (left<right){
            while (left<right && ints[right]>=pivot){
                right--;
            }
            ints[left] = ints[right];

            while (left<right && ints[left]<=pivot){
                left++;
            }
            ints[right] = ints[left];
        }
        //退出循环 则左边比pivot小，右边比pivot大
        ints[left] = pivot;
        return left;
    }

    public static void printInts(int[] ints){
        for (int anInt : ints) {
            System.out.print(anInt+"\t");
        }
    }

}
