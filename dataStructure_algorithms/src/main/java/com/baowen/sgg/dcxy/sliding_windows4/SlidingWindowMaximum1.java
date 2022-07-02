package com.baowen.sgg.dcxy.sliding_windows4;

import javax.lang.model.element.VariableElement;
import java.util.*;

/**
 * 239
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回 滑动窗口中的最大值 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * 示例 2：
 *
 * 输入：nums = [1], k = 1
 * 输出：[1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sliding-window-maximum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author mangguodong
 * @create 2022-06-28
 */
public class SlidingWindowMaximum1 {

    public static void main(String[] args) {
        int[] nums = new int[]{ 1,3,-1,-3,5,3,6,7};
        int k = 3;

        int[] nums1 = new int[]{ 1};
        int k1 = 1;
        int[] ints = slidingWindowMaximum(nums, k);
        for (int anInt : ints) {
            System.out.println(anInt);
        }


    }

    /**
     * 空间换时间
     *
     * 左右扫描
     * 思路:
     *      需要一点数学推导
     *          1,3,-1,-3,5,3,6,7
     *
     *      从左到右按K分块 每个位置都是块内到目前位置的最大值    left[]
     *          1,3,3,-3,5,5,6,7
     *      从右到左按K分块 ，每个位置都是块内到目前位置的最大值  （方向与上面相反）  right[]
     *          3,3,-1,5,5,3,7,7
     *      在一个窗口内  如果是从i到j 就满足   max = max（right[i],left[j]）
     *      例如：
     *          1,3,-1   left[j] = -1  right[i] = 3  max(right,left) = 3 是对的
     *          3,-1,-3  left[j] = -3  right[i] = 3  max(right,left) = 3 是对的
     *
     *
     *
     * @param ints
     * @param k
     * @return
     */
    public static int[] slidingWindowMaximum(int[] ints, int k){
        int n = ints.length;
        int[] result = new int[n - k + 1];

        int[] left = new int[n];
        int[] right = new int[n];

        for (int i = 0; i < n; i++) {

            if(i%k==0){
                left[i] = ints[i];
            }else {
                // 例 1,3,-1  3和1比较
                left[i] = Math.max(ints[i],ints[i-1]);
            }

            int j = n-1-i;

            if(j%k == k-1 || j==n-1){
                right[j] = ints[j];
            }else {
                // -3,5,3  中 5和 3比较
                right[j] = Math.max(ints[j],ints[j+1]);
            }
        }

        for (int i = 0; i <= n-k ; i++) {
            result[i] = Math.max(right[i],left[i+k-1]);
        }

        return result;

    }


    /**
     * 空间换时间
     * 巧用数据结构-使用双队列
     *
     * 思路:  没遍历一个数，将其所在窗口的最大值存入队列，  此队列队首永远要是 当前窗口的最大值，同时每加一个数，将比这个数小的数删除
     *
     * 心路历程：同时每加一个数，将比这个数小的数删除  这个说实话，有点难想到 ；算法题多做多见识把
     * 上个窗口最大值是上个窗口的首位的话，需要将队列的最大值删除  这也是个细节 需要处理
     *
     * 时间复杂度： O(n)  while 对 外层的 i  来说 最多一次 进队 一次出队  是 常数   所以是 n*2  还是 O(n)
     *
     * @param ints
     * @param k
     * @return
     */
    public static int[] slidingWindowMaximum4(int[] ints, int k){
        int n = ints.length;
        int[] result = new int[n - k + 1];

        // 队列存数组的索引
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        //初始化双队列

        for (int i = 0; i <k ; i++) {

            while (!deque.isEmpty() && ints[i]>ints[deque.getLast()]){
                deque.removeLast();
            }

            deque.addLast(i);
        }
        result[0] = ints[deque.getFirst()];

        //遍历数组求每个窗口最大值
        //每遍历一个数，就是操作这个窗口，就取这个窗口的最大值
        for (int i = k; i <n ; i++) {

            //上个窗口最大值是上个窗口的首位的话，需要将队列的最大值删除
            //因为上个窗口最大值且是首位的话，不能作为这个窗口的最大值
            if(!deque.isEmpty() && deque.getFirst() == i-k){
                deque.removeFirst();
            }

            while (!deque.isEmpty() && ints[i]>ints[deque.getLast()]){
                deque.removeLast();
            }

            deque.addLast(i);

            result[i-k+1] = ints[deque.getFirst()];

        }

        return result;
    }


    /**
     * 空间换时间
     * 巧用数据结构解决算法问题 -使用大顶堆解决问题
     *
     * 长度为 n 的数组，里面有多少个窗口
     * 0到 n-1     最后一个窗口的起始终点位置 n-1-k+1（即n-k） 到 n-1     也即由0到n-k 个窗口 即 有 n-k+1 个窗口
     *
     * 时间复杂度是
     *  取决于大顶堆 增加元素和删除元素  时 保持最大元素在最顶上的 交换次数，
     *  大顶堆是 完全二叉树，交换次数为树的深度  即k为元素个数，log（k） 为树的深度
     *
     *  所以 算法复杂度 为 O((n-k)*logk) =>  O(n*logk-klogk)   k很小就是 n*logk
     *
     * @param ints
     * @param k
     * @return
     */
    public static int[] slidingWindowMaximum3(int[] ints, int k){
        int n = ints.length;
        int[] result = new int[n - k + 1];

        //采用优先队列实现一个大顶堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                //倒序排就是 后面减前面
                return i2 - i1;
            }
        });

        //初始化大顶堆
        for (int i = 0; i <k ; i++) {
            maxHeap.add(ints[i]);
        }
        result[0] = maxHeap.peek();

        //利用大顶堆特性求解窗口内的最大致

        for (int i = 1; i <= n-k; i++) {
            maxHeap.remove(ints[i-1]);
            maxHeap.add(ints[i+k-1]);
            result[i] = maxHeap.peek();

        }

        return result;

    }

    /**
     * 暴力法解
     * 长度为 n 的数组，里面有多少个窗口
     *  0到 n-1     最后一个窗口的起始终点位置 n-1-k+1（n-k） 到 n-1     也即由0到n-k 个窗口 即 有 n-k+1 个窗口
     *  假设是4的数组窗口大小为3
     *
     * 思路：
     *      从头遍历所有窗口，窗口内求最大值放入 结果数组中
     *
     * 时间复杂度 O ((n-k)*k) => O (nk-k^2)  如果k很小，就是 nk  即o(n) 如果k接近于n    n^2-k^2   是平方的复杂度
     *
     * @param ints
     * @param k
     * @return
     */
    public static int[] slidingWindowMaximum2(int[] ints, int k){

        int n = ints.length;
        int[] result = new int[n - k + 1];

        for (int i = 0; i <=n-k ; i++) {

            int max = ints[i];
            //对每个窗口求最大值，放入结果数组中
            for (int j = i; j <=i+k-1 ; j++) {

                if(ints[j]>=max){
                    max = ints[j];
                }
            }
            result[i] = max;
        }
        return result;

    }





    /**
     * 这题是自己做的 暴力法
     * 思路：
     * // 传入 数组  以及数组的 开始位置，返回 一个 2位的数组， 中间记录这 窗口内的最大值 以及 是否继续下一次滑窗操作
     *
     * 虽然自己写出来了，但是调试了蛮久，代码一看上去思路不清晰，说明自己解题思路不是很清晰，暴力法这么简单的思路为啥要写这么多代码
     *      刷的题少，导致自己解题没有章法
     *
     *
     * @param ints
     * @param k
     * @return
     */
    public static int[] slidingWindowMaximum1(int[] ints, int k){

        // 传入 数组  以及数组的 开始位置，返回 map  map里面有当前窗口最大值 ，以及是否继续 调用
        int start = 0;
        ArrayList<Integer> list = new ArrayList<>();
        int[] iis = getMaxNum(ints, start, k);
        list.add(iis[1]);
        System.out.println("循环前");
        //
        while (iis[0]!=0&& start<ints.length-k){
            System.out.println("进入循环");
            iis = getMaxNum(ints,++start,k);
            list.add(iis[1]);
        }


        int[] r = new int[ints.length - k+1];
        for (int i = 0; i < list.size(); i++) {
            r[i] = list.get(i);
        }

        return r;
    }

    public static int[] getMaxNum(int[] ints,int start,int windowlength){

        int len = ints.length;

        if(len-1-start >= windowlength-1){
            int max = ints[start];
            int mid = start + windowlength - 1;
            for(int i = start;i<= mid;i++){
                if(ints[i]>=max){
                    max = ints[i];
                }
            }
            System.out.println("max = " + max + " start = " + start + " mid = " + mid);
            return new int[]{1,max};
        }else {
            return new int[]{0,0};
        }

    }

}
