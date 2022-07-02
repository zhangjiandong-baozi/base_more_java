package com.baowen.sgg.dcxy.sliding_windows4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *

 * 注意：
 *
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *  
 *
 * 示例 1：
 *
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 示例 2：
 *
 * 输入：s = "a", t = "a"
 * 输出："a"
 *
 * 提示：
 *
 * 1 <= s.length, t.length <= 105
 * s 和 t 由英文字母组成
 *  
 *
 * 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-window-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-window-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author mangguodong
 * @create 2022-07-01
 */
public class MinWindowSubstring2 {


    public static void main(String[] args) {

        String  s1 = "ADOBECODEBANC";
        String  t = "ABC";

       // String substring = t.substring(2, 1);
       // System.out.println(substring);

         System.out.println(minWindow(s1, t));


    }


    /**
     *
     * 思路：
     *      先将s2 排序  得到 s2_
     *      对s1 进行拆分，每个子字符创都是 s2大小，且对 拆分的子串进行排序  如果  s2_  == 子串 则返回 s1 子串的下标
     *          假如 最小子串不符合目标，则扩大子串的长度重复上面的操作
     *
     * @param s1
     * @param s2
     * @return
     */
    public static String minWindow(String s1,String s2){
        System.out.println("========");

        String s2_ = rankString(s2)  ;
        int s2_len_ = s2.length();
        int  s2_len = s2_len_;
        String result = "";

        HashMap<String, Integer> map = new HashMap<>();
        Boolean flag = true;
        while (flag && s2_len<=s1.length()){
            for (int i = 0; i <=s1.length()-s2_len ; i++) {
                map.put(rankString(s1.substring(i,i+s2_len)).substring(0,s2_len_),i);
            }
            System.out.println("map.size() = " + map.size());
            System.out.println("map = " + map);
            System.out.println("s2_ = " + s2_);

            if(map.containsKey(s2_)){

                System.out.println(s2_);
                System.out.println("map.get(s2_) = " + map.get(s2_));
                System.out.println(s1.substring(map.get(s2_),map.get(s2_)+s2_len));
                result = s1.substring(map.get(s2_),map.get(s2_)+s2_len);
                flag = false;
            }
            map.clear();
            s2_len++;
            System.out.println("s2_len = " + s2_len);
        }


        return result;
    }

    /**
     * 返回字符串字典序的结果
     * @return
     */
    public static String rankString(String str){

        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }


}
