package com.baowen.sgg.dcxy.sort8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * #56
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2：
 *
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *  
 *
 * 提示：
 *
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/merge-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author mangguodong
 * @create 2022-08-11
 */
public class MergeIntervals {

    public static void main(String[] args) {
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};

        for (int[] interval : mergeIntervals(intervals)) {
            SortColors.printArray(interval);
        }
    }

    /**
     *
     * [1,4] [2,6]  满足  1<2<4 这种才能合并
     *
     * 又 将2维数组 按照左边排序后， 满足这种情况的 进行合并
     *
     * @param ints
     * @return
     */
    public static int[][] mergeIntervals(int[][] ints){

        //放合并好的数组
        List<int[]> result = new ArrayList<>();

        Arrays.sort(ints, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });

        for (int[] anInt : ints) {
            int left = anInt[0],right = anInt[1];
            int size = result.size();
            //假设 集合没元素 ，或者 后一个 数组的左元素大于集合 最后一个数组的 左元素 ，则无法合并
            if(size==0 || left>result.get(size-1)[1]){
                result.add(anInt);
            }else{

                int mergeLeft = result.get(size-1)[0];
                int mergeRight = Math.max(result.get(size-1)[1],right);
                result.set(size-1,new int[]{mergeLeft,mergeRight});

            }
        }

        return result.toArray(new int[result.size()][]);

    }

}
