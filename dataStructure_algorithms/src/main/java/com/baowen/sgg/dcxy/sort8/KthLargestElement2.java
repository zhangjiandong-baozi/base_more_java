package com.baowen.sgg.dcxy.sort8;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * 找出倒数第K大的元素
 *
 * @author mangguodong
 * @create 2022-08-04
 */
public class KthLargestElement2 {

    public static void main(String[] args) {
        int[] nums1 = {3,2,1,5,6,4};
        int[] nums2 = {3,2,3,1,2,4,5,5,6};
        int[] nums3 = {1,9,8,7,3,4,5,6,2};
//
//        System.out.println(kthLargestElement3(nums1,2));
//        System.out.println(kthLargestElement3(nums2,2));
        System.out.println(kthLargestElement3(nums3,2));
    }

    /**
     * 堆排序,构建大顶堆
     * 删除大顶堆k-1个元素，取第一个就是第k大的元素
     *
     * 时间复杂度 n*logn
     *
     * 空间复杂度 logn
     * @param ints
     * @return
     */
    public static int kthLargestElement3(int[] ints,int k){
        //大顶堆的大小
        int heapSize = ints.length;

        buildMaxHeap(ints,heapSize);

        for (int i = ints.length-1; i >ints.length-k ; i--) {

            //将堆顶元素交换到末尾
            swap(ints,0,i);
            heapSize--;
            maxHeapify(ints,0,heapSize);
        }
        return ints[0];
    }

    /**
     * 利用maxHeapify 来构建大顶堆
     * 思路:
     *      从下往上 对每个非叶子节点进行maxHeapify(即找最大节点) 操作
     *      数组下标前2/n-1的节点肯定是 非叶子节点
     *
     *      时间复杂度是 n*logn   n个元素上浮的过程
     * @param ints
     * @param heapSize
     */
    public static void buildMaxHeap(int[] ints, int heapSize) {

        for (int i = heapSize/2-1; i >= 0; i--) {
            System.out.println("i = " + i);
            maxHeapify(ints,i,heapSize);
        }

    }

    /**
     * 思路：
     *      在一个3节点的 二叉树 中  ,头结点和左右节点比较，大的和头结点互换位置
     *      又一个数组构建二叉树中，左右节点和头结点有关系 是2倍的关系
     *
     *      下沉操作是 k*logn
     *
     * @param ints
     * @param top
     * @param heapSize
     */
    public static void maxHeapify(int[] ints, int top, int heapSize) {

        // 数组中头结点的左右节点索引和头结点索引有规律可循
        int left = 2*top+1;
        int right = 2*top+2;

        int largest = top;
        if(left<heapSize && ints[left]>ints[largest]){
            largest = left ;
        }
        if(right<heapSize && ints[right]>ints[largest]){
            largest = right;
        }

        if(top!=largest){
            swap(ints,top,largest);
            //继续调用循环交换
            maxHeapify(ints,largest,heapSize);
        }
    }


    //使用快排思路解决
    //即直接选择排序

    /**
     *
     * 思路：
     *      快排的思路是，找到一个值，让它的左边都比自己小，右边比自己大
     *      第k大可以直接借鉴这个思路
     *          这个值比k大，就从0-这个值区间找
     *          这个值比k小 ，那么从这个值到末尾 找，每次找这个值返回这个值的索引下标
     *
     * 时间复杂度： 数学期望比快排小 算法导论第9章   数学证明了 是O(n)
     * 空间复杂度 logn
     *
     * @param ints
     * @param k
     * @return
     */
    public static int kthLargestElement2(int[] ints,int k){

        return quickSelect(ints,0,ints.length-1,ints.length-k);

    }

    public static int quickSelect(int[] ints, int start, int end, int index) {

        int position = randomPatition(ints,start,end);

        if(index==position){
            return ints[position];
        }else {
            return position>index?quickSelect(ints,start,position-1,index):quickSelect(ints,position+1,end,index);
        }
    }

    private static int randomPatition(int[] ints, int start, int end) {
        Random random = new Random();
        int randIndex = start+random.nextInt(end-start+1);
        swap(ints,start,randIndex);
        return MyQuickSort1.partiton1(ints,start,end);

    }

    public static void swap(int[] nums,int i,int j){
        if(i>nums.length-1 || j>nums.length-1){
            throw new RuntimeException("数组交换索引位置不在");
        }
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    public static int kthLargestElement1(int[] ints,int k){
        Arrays.sort(ints);
        return ints[ints.length-k];
    }






}
