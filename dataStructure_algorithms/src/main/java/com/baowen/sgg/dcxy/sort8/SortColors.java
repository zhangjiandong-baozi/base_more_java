package com.baowen.sgg.dcxy.sort8;

/**
 * #75
 * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 *
 * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 * 必须在不使用库的sort函数的情况下解决这个问题。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 * 示例 2：
 *
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 *  
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] 为 0、1 或 2
 *  
 *
 * 进阶：
 *
 * 你可以不使用代码库中的排序函数来解决这道题吗？
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sort-colors
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author mangguodong
 * @create 2022-08-11
 */
public class SortColors {
    public static void main(String[] args) {
        int[] ints = new int[]{2,0,2,1,1,0};
        //sortColors1(ints);
        //sortColors2(ints);
        sortColors3(ints);
        printArray(ints);

    }

    /**
     * 借鉴快速排序双指针法
     *
     * 如果有4给数咋办？  递归实现？
     * 思路:
     *      遍历数组的过程中,是2就将它排到后面，是0将它放到前面
     * @param nums
     */
    public static void sortColors3(int[] nums){

        int left=0,right =nums.length-1;
        int i  = left;

        while (left<right && i<=right){
            //如果是2，换到末尾，右指针左移
            //0 1 2 0 1 2 2  => 0 1 1 0 2 2 2
            //  即 下标为2的2  ，要右指针移动2次后才交换成1
            while(nums[i]==2 && i<=right){
                KthLargestElement2.swap(nums,i,right);
                right--;
            }

            // 2. 如果是0，换到头部，左指针右移
            if(nums[i]==0){
                KthLargestElement2.swap(nums,i,left);
                left++;
            }
            i++;
        }

    }


    /**
     * 计数排序
     *
     * 思路:
     *      计算每个值的个数，遍历放入数组
     *
     *时间复杂度：O（n）  遍历2遍数组
     * @param nums
     */
    public static void sortColors2(int[] nums){
        int count1=0,count2=0,count3=0;
        for (int i = 0; i < nums.length; i++) {

            if(nums[i]==0){
                count1++;
            }else if(nums[i]==1){
                count2++;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if(i<count1){
                nums[i] = 0;
            }else if(i<count2+count1){
                nums[i]=1;
            }else {
                nums[i]=2;
            }
        }

    }

    /**
     * 借鉴选择排序的思路
     *
     *
     * 一遍循环将0放在前面
     * 一遍循环将1放到中间
     *
     * 时间复杂度:
     *      O(n) 2遍扫描
     * @param nums
     */
    public static void sortColors1(int[] nums){

        // 定义一个指针，指向当前应该填入元素的位置
        int cur = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]==0){
                KthLargestElement2.swap(nums,i,cur);
                cur++;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]==1){
                KthLargestElement2.swap(nums,i,cur);
                cur++;
            }
        }
    }

    public static void printArray(int[] ints){

        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i]+"\t");
        }
    }
}
