package com.baowen.sgg.dcxy.binary_search2;

import java.util.*;

/**
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
 *
 * 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
 *
 * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,4,2,2]
 * 输出：2
 * 示例 2：
 *
 * 输入：nums = [3,1,3,4,2]
 * 输出：3
 * 提示：
 *
 * 1 <= n <= 105
 * nums.length == n + 1
 * 1 <= nums[i] <= n
 * nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次
 *  
 *
 * 进阶：
 *
 * 如何证明 nums 中至少存在一个重复的数字?
 * 你可以设计一个线性级时间复杂度 O(n) 的解决方案吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-the-duplicate-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author mangguodong
 * @create 2022-05-19
 */
public class FindDuplicatedNumber3 {

    public static void main(String[] args) {

        int[] i1s = {1,3,4,2,2};
        int[] i2s = {3,1,3,4,2};

//        System.out.println("FindDuplicatedNumber(i1s) = " + FindDuplicatedNumber(i1s));
//        System.out.println("FindDuplicatedNumber(i2s) = " + FindDuplicatedNumber(i2s));

//        System.out.println("FindDuplicatedNumberByMap(i1s) = " + FindDuplicatedNumberByMap(i1s));
//        System.out.println("FindDuplicatedNumberByMap(i2s) = " + FindDuplicatedNumberByMap(i2s));
//        System.out.println("FindDuplicatedNumberByMap1(i1s) = " + FindDuplicatedNumberByMap1(i1s));
//        System.out.println("FindDuplicatedNumberByMap1(i2s) = " + FindDuplicatedNumberByMap1(i2s));
//        System.out.println("FindDuplicatedNumberBySort(i1s) = " + FindDuplicatedNumberBySort(i1s));
//        System.out.println("FindDuplicatedNumberBySort(i2s) = " + FindDuplicatedNumberBySort(i2s));
//        System.out.println("FindDuplicatedNumberByBinary(i1s) = " + FindDuplicatedNumberByBinary(i1s));
//        System.out.println("FindDuplicatedNumberByBinary(i2s) = " + FindDuplicatedNumberByBinary(i2s));
        System.out.println("FindDuplicatedNumberByTwoPoint(i1s) = " + FindDuplicatedNumberByTwoPoint(i1s));
        System.out.println("FindDuplicatedNumberByTwoPoint(i2s) = " + FindDuplicatedNumberByTwoPoint(i2s));

    }

    //我想到了2数之和 放在map里面 找
    //先试试暴力法
    public static int FindDuplicatedNumber(int[] nums){

        for (int i = 0; i < nums.length-1; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if(nums[i]==nums[j]){
                    return nums[i];
                }
            }
        }

        throw new RuntimeException("此数组没有重复元素");
    }

    /**
     * 好像用map求解，并不行
     * 为啥2数之和可以用呢  因为fori 循环 中的i 和双数之和 是 确定的从而能推导出另一个数，而 我这个target 不确定，下标i 知道，但是target无从得知
     *
     * 后面听课，这个思路又可以了，key  和 value  自己原来设置的过于复杂，2数之和的关键是找原来map里面有没有这个数，即contains方法才是关键的
     *
     * 题目要求空间复杂度是O1  ，所以一下求解有点问题
     * @param nums
     * @return
     */
    public static int FindDuplicatedNumberByMap(int[] nums){

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            if(map.containsKey(nums[i])){
                System.out.println("nums[i] = " + nums[i]);
                map.put(nums[i],map.get(nums[i])+1);
                continue;
            }
            map.put(nums[i],1);
        }
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        for (Map.Entry<Integer, Integer> entry : entries) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println("key= "+key +" value = " + value);
            if(value>=2){
                return key;
            }
        }
        throw new RuntimeException("此数组没有重复元素");
    }

    /**
     * 时间复杂度是 On  空间复杂度是  On
     * 使用set也是一样的求解思路
     *
     * @param nums
     * @return
     */
    public static int FindDuplicatedNumberByMap1(int[] nums){

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            if(map.containsKey(nums[i])){
                return nums[i];

            }
            map.put(nums[i],1);
        }
        throw new RuntimeException("此数组没有重复元素");
    }

    /**
     * sort改变了原来的数组，不符合题目要求
     *
     * @param nums
     * @return
     */
    public static int FindDuplicatedNumberBySort(int[] nums){

        Arrays.sort(nums);
        //为什么要从i=1 开始,因为i=0开始 当i = length-1 时,n+1 超过数组长度会报错
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] == nums[i-1]){
                return nums[i];
            }
        }

        return -1;
    }


    /**
     * 双指针
     * 先将数组排序，   如果0-mid 的长度大于 mid-1 说明在这个范围， 即  mid>a[mid] 说明在 0-mid之间
     *
     * -------
     * 上面我想到基于特殊问题，寻找其数学规律解决
     *
     * 下面是基于数学方法的二分查找解决此问题  时间复杂度是 O（nlogn）
     *
     * 如果重复数（比如叫做target）只出现两次，那么其实就是1~N所有数都出现了一次，然后再加一个target；
     * 如果重复数target出现多次，那在情况1的基础上，它每多出现一次，就会导致1~N中的其它数少一个。
     *
     * 例如：1~9之间的10个数的数组，重复数是6：
     * 1，2，5，6，6，6，6，6，7，9
     * 本来最简单（重复数出现两次，其它1~9的数都出现一次）的是
     * 1，2，3，4，5，6，6，7，8，9
     * 现在没有3、4和8，所以6会多出现3次。
     *
     * 我们可以发现一个规律：
     * 以target为界，对于比target小的数i，数组中所有小于等于它的数，最多出现一次（有可能被多出现的target占用了），所以总个数不会超过i。
     * 对于比target大的数j，如果每个元素都只出现一次，那么所有小于等于它的元素是j个；而现在target会重复出现，所以总数一定会大于j。
     * 用数学化的语言描述就是：
     * 我们把对于1~N内的某个数i，在数组中小于等于它的所有元素的个数，记为count[i]。
     * 则：当i属于[1, target-1]范围内，count[i] <= i；当i属于[target, N]范围内，count[i] > i。
     *
     * 即  1 6 7 8 6 3 4 6 2  中  target 为 6     ;
     * 比6 小的数 例如  4（1-n的范围）  出现的元数个数 <=4(1、2、3、4)即4个   3 出现的元素个数<=3(1、2、3) 即3个
     * 比6大的数 例如 7 出现元素个数 >7 (1/2/3/4/6/6/6/7) 即8 个   8出现的元素个数>8 即9 个
     *
     * @param nums
     * @return
     */
    public static int FindDuplicatedNumberByBinary(int[] nums){

        //数组的范围是1-N
        // 元素的序列范围也是 1-N
        // 而数组的下标个数是 n 个 即 0-n
        //查找1~N的自然数序列，寻找target
        int left = 1;
        int right = nums.length-1;

        while(left<=right){

            int mid = (left+right)/2;
            int count = 0;
            //算count数
            for (int j = 0; j < nums.length; j++) {
                if(nums[j]<=mid) {
                    count++;
                }
            }

            //count 的数 小于等于mid  ，说明target 在 右边，反之target 在左边
            if(count<=mid){
                left = mid +1;
            }else {
                right = mid;
            }

            // 当左右指针相遇 ，说明 找到target
            if(left == right){
                return left;
            }

        }

        return  -1;
    }


    /**
     * 快慢指针求解 时间复杂度是O(n)
     *
     * 将此类数组[3，6，1，4，6，6，2] =>看成链表 (value,point)   即 数组的下标是链表的value值，数组的值是链表的point指针
     *
     *                                (1,6)
     *                               ↓     ↑
     * (0,3) -> (3,4) -> (4,6) -> (6,2) ->(2,1)
     *
     * 即数组的值如果有重复的,在链表中会有多个指针指向同一个元素，链表必定形成闭环
     *
     * 在一个环内，兔子会再次追上乌龟，即快指针会再次遇到慢指针   ，可以找到相遇位置
     *
     * 从起始位置到入口位置的距离 等于 相遇位置到环内入口位置的距离（数学证明）  而环内入口的值（链表）（或上个链表的指针相同）就是重复值
     *
     *
     * 专业说明如下：
     * 第一阶段，寻找环中的节点
     * a)初始时，都指向链表第一个节点nums[0]；
     * b)慢指针每次走一步，快指针走两步；
     * c)如果有环，那么快指针一定会再次追上慢指针；相遇时，相遇节点必在环中
     * 第二阶段，寻找环的入口节点（重复的地址值）
     * d)重新定义两个指针，让before，after分别指向链表开始节点，相遇节点
     * e)before与after相遇时，相遇点就是环的入口节点
     * 第二次相遇时，应该有：
     * 慢指针总路程 = 环外0到入口 + 环内入口到相遇点 (可能还有 + 环内m圈)
     * 快指针总路程 = 环外0到入口 + 环内入口到相遇点 + 环内n圈
     * 并且，快指针总路程是慢指针的2倍。所以：
     * 环内n-m圈 = 环外0到入口 + 环内入口到相遇点。
     * 把环内项移到同一边，就有：
     * 环内相遇点到入口 + 环内n-m-1圈 = 环外0到入口
     *
     * 这就很清楚了：从环外0开始，和从相遇点开始，走同样多的步数之后，一定可以在入口处相遇。所以第二阶段的相遇点，就是环的入口，也就是重复的元素。
     *
     * @param nums
     * @return
     */
    public static int FindDuplicatedNumberByTwoPoint(int[] nums) {

        // 快慢指针走环一定会相遇
        // 快慢指针一开始也是相遇的，所以while 循环不能用，快慢指针 要多先走一步才能开始循环
        int slow = 0;
        int fast = 0;
        do {
             //慢指针走1步
             slow = nums[slow];
             //快指针走2步
             fast = nums[nums[fast]];
        }while (slow != fast);

        //从起始位置到入口位置的距离 等于 相遇位置到环内入口位置的距离（数学证明）  而环内入口的值（链表）（或上个链表的指针相同）就是重复值
        int start = 0;
        int meet = slow;

        while (start!=meet){
            start = nums[start];
            meet = nums[meet];
        }

        return meet;

    }
}
