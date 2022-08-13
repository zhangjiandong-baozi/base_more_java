package com.baowen.sgg.dcxy.stack_and_queue7;


import java.util.Stack;

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 *  
 *
 * 示例 1:
 *
 *
 *
 * 输入：heights = [2,1,5,6,2,3]
 * 输出：10
 * 解释：最大的矩形为图中红色区域，面积为 10
 * 示例 2：
 *
 *
 *
 * 输入： heights = [2,4]
 * 输出： 4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/largest-rectangle-in-histogram
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author mangguodong
 * @create 2022-07-24
 */
public class LargestRectangleInHistorgram4 {

    public static void main(String[] args) {
        /**
         * 刚开始的思路是 从低到高 的高度 结合长度 算面积
         *
         * 总结
         *     对问题的分析要比较透彻和深刻，有一个解决思路以后,对于多余的重复操作，探究他判断的边界条件进行优化。要养成不断进取探究的习惯。
         *
         */
        int[] heights =  new int[]{2,1,5,6,2,3};
        System.out.println(LargestRectangleInHistorgram6(heights));
    }


    /**
     * LargestRectangleInHistorgram4是双重循环，能不能单循环搞定
     *
     *
     * @param ints
     * @return
     */
    public static int LargestRectangleInHistorgram6(int[] ints){
        int len = ints.length;

        int maxArea = 0;

        Stack<Integer> stack = new Stack<>();

        int leftArr[] = new int[len];
        int rightArr[] = new int[len];
        // 初始化rights为右哨兵n
        for (int i = 0; i < len; i ++) rightArr[i] = len;
        for (int i = 0; i < len; i++) {
            //stock 只会存比当前数小的数的索引
            while(!stack.isEmpty()&& ints[stack.peek()]>=ints[i]){
                // 从 找右边界看,栈顶元素如果小于当前元素，那么它的右边界就是当前元素
                rightArr[stack.peek()] = i;
                stack.pop();
            }
            //
            leftArr[i] = stack.isEmpty()?-1:stack.peek();
            stack.push(i);
        }

        stack.clear();
        for (int i = len-1; i >=0; i--) {
            //stock 只会存比当前数小的数的索引
            while(!stack.isEmpty()&& ints[stack.peek()]>=ints[i]){
                stack.pop();
            }
            //
            rightArr[i] = stack.isEmpty()?len:stack.peek();
            stack.push(i);
        }

        for (int i = 0; i < len; i++) {
            //求当前单元矩形的高度
            int currHeight = ints[i];
            int currArea = (rightArr[i]-leftArr[i]-1)*currHeight;
            maxArea = Math.max(currArea,maxArea);
        }
        return maxArea;
    }

    /**
     * 使用单调栈
     *
     * 思路:
     *      6,7,5,2,4,5,9,3
     *      栈的目的是啥？ 保存整个数组 最小数 以及较小数的  ，同时有个左数组记录目标数的每个数左边界值
     *      以左边界为例,   2的左边的元素都比2大，那么它的左边界是-1，
     *
     * 时间复杂度分析：
     *      找左边界是遍历数组是O（n） ,内层while 循环  最多对元素 做2个操作，所以 整体的 复杂度还是 O(n)
     *
     *
     * @param ints
     * @return
     */
    public static int LargestRectangleInHistorgram5(int[] ints){

        int len = ints.length;

        int maxArea = 0;

        Stack<Integer> stack = new Stack<>();

        int leftArr[] = new int[len];
        int rightArr[] = new int[len];

        for (int i = 0; i < len; i++) {
            //stock 只会存比当前数小的数的索引
            while(!stack.isEmpty()&& ints[stack.peek()]>=ints[i]){
                stack.pop();
            }
            //
            leftArr[i] = stack.isEmpty()?-1:stack.peek();
            stack.push(i);
        }

        stack.clear();
        for (int i = len-1; i >=0; i--) {
            //stock 只会存比当前数小的数的索引
            while(!stack.isEmpty()&& ints[stack.peek()]>=ints[i]){
                stack.pop();
            }
            //
            rightArr[i] = stack.isEmpty()?len:stack.peek();
            stack.push(i);
        }

        for (int i = 0; i < len; i++) {
            //求当前单元矩形的高度
            int currHeight = ints[i];
            int currArea = (rightArr[i]-leftArr[i]-1)*currHeight;
            maxArea = Math.max(currArea,maxArea);
        }
        return maxArea;
    }

    /**
     * 双指针优化  优化for内部的循环   即每次找左右边界有点冗余 可优化
     *思路：
     *      例如 32165894
     *          对于5而言 它的左边界是1，但同时也是6的左边界
     *          即 只要 当前数小于 左边的数 ，那么它的左边界 就是 它左边数的左边界
     *          同理右边界也一样
     *
     *     遍历目标数组 求每个数的面积  即左右边界，和它的有效高度相乘 ，得到 最大的面积
     *
     * 时间复杂度分析：
     *      寻找每个数左边界  遍历了整个数组O(n),
     *          其中while循环是 O(1)  , 判断取左边界时，要不就是左边数的左边界（5的左边界是6的左边界，之前已经求好了），要不就是左边那个数（6的左边界是1）
     *
     *      寻找每个数的右边界 O(n)
     *
     *      计算每个数的最大面积 O(n)
     *
     *      即 3个O（n） 即 时间复杂度为O（n）
     *
     * @param ints
     * @return
     */
    public static int LargestRectangleInHistorgram3(int[] ints){

        int len = ints.length;
        int maxArea = 0;

        int[] leftArr = new int[len];
        int[] rightArr = new int[len];

        //循环数组得到每个数的左边界
        for (int i = 0; i < len; i++) {
            int left = i-1;
            while (left>=0){
                // 以6为例  6大于1 退出while判断
                if(ints[i]>ints[left]) break;
                //以5为例  5的左边界就是6的左边界

                left = leftArr[left];
            }
            //6的左边界是1的索引
            leftArr[i] = left;

        }

        //循环数组，得到每个数的右边界
        for (int i = len-1; i > 0; i--) {
            int right = i+1;
            while (right<len){
                // 以6为例  6大于1 退出while判断
                if(ints[i]>ints[right]) break;
                //以5为例  5的左边界就是6的左边界
                right = rightArr[right];
            }
            //6的左边界是1的索引
            rightArr[i] = right;

        }

        for (int i = 0; i < len; i++) {
            //求当前单元矩形的高度
            int currHeight = ints[i];
            int currArea = (rightArr[i]-leftArr[i]-1)*currHeight;
            maxArea = Math.max(currArea,maxArea);
        }

        return maxArea;
    }

    /**
     * 暴力法（双指针）
     *  遍历高度求解
     *  思路:
     *      找到一个矩阵，从左右2个方向扩展，  留下面积最大的
     *
     * 时间复杂度分析：
     *      最坏的情况       中间某个单元矩形，左边的都比它高，右边的都比它高   for内部的循环就是 二分之n的左循环 反之 右边也一样
     *      所以整体的复杂度是 O（n^2）
     * @param ints
     * @return
     */
    public static int LargestRectangleInHistorgram2(int[] ints){

        int maxArea = 0;

        for (int i = 0; i < ints.length; i++) {

            int left = i,right = i;
            int currHeight = ints[i];

            while (left>=0){
                if(ints[left]<currHeight) break;
                left --;

            }

            while (right<ints.length){
                if(ints[right]<currHeight) break;
                right ++;
            }

            int currArea = (right-left-1)* currHeight;

            maxArea = Math.max(currArea,maxArea);

        }

        return maxArea;
    }
    /**
     *
     * 思路:
     * 遍历所有的宽度
     *
     * 时间复杂度 O（n^2）
     *
     * @param ints
     * @return
     */
    public static int LargestRectangleInHistorgram1(int[] ints){

        int maxArea = 0;
        for (int i = 0; i < ints.length; i++) {
            //取最小的高度
            int currHeight= ints[i];
            for (int j = i; j < ints.length; j++) {   // j代表右边界
                //右移的过程中选高度最小的
                currHeight = Math.min(currHeight,ints[j]);
                int currArea = (j-i+1)*currHeight;
                maxArea = Math.max(maxArea,currArea);
            }
        }
        return maxArea;
    }
}
