package com.baowen.sgg.dcxy.arrays;

import java.util.Arrays;

/**
 * 下一个队列
 *
 * 整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。
 *
 * 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
 * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
 *
 * 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
 * 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
 * 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
 * 给你一个整数数组 nums ，找出 nums 的下一个排列。
 *
 * 即找到下一个排列比现在这个排列大，且比之后所有排列小的数列
 * 例如 123  接下来时 132  而不是 213
 *
 * 观看到 29分 暴力法 时间复杂度N!(Ann的全排列是N！)  空间复杂度是O(n) (难道是 new一个数组存下一个排列？)  不懂
 *
 * 排列组合既然是高中的知识，忘了， 我明天 看看应该能弄懂 ，
 *
 * 观看视频51:12 时，我发现这个问题的算法，比较特殊，，仅仅只有它有 特有的规律，不适用于其他类型的算法，
 * 即分治递归等等算法思想好像解决不了，需要从问题本身的规律来求解
 *
 * 吴军老师的计算之魂的归纳 总结中，有没有提到这类现象呢？
 *
 * （或者说要找到特定问题的规律，从规律上解决它）
 *
 *
 * @author mangguodong
 * @create 2022-05-15
 */
public class NextPermutation03 {

    public static void main(String[] args) {

//        int[] nums = {1,2,3};
        int[] nums = {1,5,3,4,9,7};
//        nextPermutation(nums);
        nextPermutationMore(nums);

        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }

    }


    /**
     * 解题思路：
     * 暴力穷举法：即所有排列 找出那个排列比它大的， 时间复杂度 Ann =  N! 不可取
     *
     * 找此问题的规律，通过规律解决此问题
     *
     * 除去降序列这种特殊情况后
     *
     * 除全部倒序 数列外，总会在数列中 找到升序子序列，将升序子序列倒排，肯定比当前数列大
     * 例  153497 中  15 倒过来 变成 513497  或者 从个位倒    349 -》 394、
     * 延伸一下 其实正确结果是  153749
     * 即  靠个位数 的最小升序子序列(49) 中  前一位(4) 与后面的 比它稍大的替换(7)，接下来的数 升序排列 7 + 49
     *
     * 步骤
     *      定位一个数k  ，从后往前找它，它是比后一个数小,从数组长度len-2 开始从数组的后面往前找
     *      找到之后，与后面数列稍大的数交换位置，然后在对交换位置后 ，对k以后的数列，升序排列
     *     6
     *         5
     *            4
     *  3
     *                 2
     *
     *
     *
     *
     *
     * @param nums
     */
    public static void nextPermutation(int[] nums){

        int len = nums.length;
        //为什么想到会用 for 啊， 当 算法 不用到每次下标时 用while 好点
//        for (int i = 0; i < len; i++) {

           // 123
            int k = len -2;

            while (k>=0 && nums[k] >= nums[k+1]){
                k--;
            }

            //表示整个数组是降序排的它是最大序列，根据题目意思，将此数列升序
            if(k ==-1){
                Arrays.sort(nums);
                return;
            }
            // 循环k以后的数列，找到 比k稍大的数字，进行交换，交换后，在将k位置以后的序列 升序排
            //从k+2 开始往后找比较好，因为k+1 本来就大于k

            // 123  的k 即下标位置是1  ,i = 3
           //  3465  345
            int i = k+2;
            // 这里是大于 才加加
            // 等于的时候 ，它前一个位置就比它大，也要结束循环
            while (i<len && nums[i] > nums[k]){
                i++;
            }

            //交换
            int temp = nums[k];
            nums[k] = nums[i-1];
            nums[i-1] = temp;

            //升序之后的子序列
//            *     6
//            *         5
//            *            4
//            *  3
//            *                 2
              // 34 交换后 ，还是降序  ，使用 双指针 调换顺序 或者 for 循环一半也行

             int start = k+1;
             int end = len-1;

             while (start<end){
                 int tmp = nums[start];
                 nums[start] = nums[end];
                 nums[end] = tmp;
                 start++;
                 len--;
             }

    }



    public static void nextPermutationMore(int[] nums){

        int len = nums.length;
        //为什么想到会用 for 啊， 当 算法 不用到每次下标时 用while 好点
//        for (int i = 0; i < len; i++) {

        // 123
        int k = len -2;

        while (k>=0 && nums[k] >= nums[k+1]){
            k--;
        }

        //表示整个数组是降序排的它是最大序列，根据题目意思，将此数列升序
        if(k ==-1){
            reverse(nums,0,len-1);
            return;
        }
        // 循环k以后的数列，找到 比k稍大的数字，进行交换，交换后，在将k位置以后的序列 升序排
        //从k+2 开始往后找比较好，因为k+1 本来就大于k

        // 123  的k 即下标位置是1  ,i = 3
        //  3465  345
        int i = k+2;
        // 这里是大于 才加加
        // 等于的时候 ，它前一个位置就比它大，也要结束循环
        while (i<len && nums[i] > nums[k]){
            i++;
        }
        //交换
        swap(nums,k,i-1);
        //升序之后的子序列
//            *     6
//            *         5
//            *            4
//            *  3
//            *                 2
        // 34 交换后 ，还是降序  ，使用 双指针 调换顺序 或者 for 循环一半也行
        reverse(nums,k+1,len-1);
    }


    //改进   Arrays.sort  是n*logn   比n 大
    //数组交换位置
    public static void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    //翻转数组
    public static void reverse(int[] nums,int start,int end){

        while (start < end){
            swap(nums,start,end);
            start++;
            end--;
        }
    }

}
