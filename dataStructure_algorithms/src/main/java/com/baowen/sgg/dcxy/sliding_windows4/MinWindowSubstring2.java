package com.baowen.sgg.dcxy.sliding_windows4;

import javax.lang.model.element.VariableElement;
import java.util.*;

/**
 * 76
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

//        HashMap<Integer,Integer> map = new HashMap<>();
//
//        map.put(1,1);
//
//        Integer integer = map.get(2);
//        System.out.println(integer);


    }


    /**
     * 在minwindow4的基础上 优化checkisSubstring 方法
     *
     * @param s1
     * @param s2
     * @return
     */
    public static String minWindow(String s1,String s2){
        //遍历目标子串的字符个数存入map中
        HashMap<Character, Integer> tmap = new HashMap<>();

        for (int i = 0; i < s2.length(); i++) {
            char tc = s2.charAt(i);
            if(tmap.containsKey(tc)){
                tmap.put(tc,tmap.get(tc)+1);
            }else {
                tmap.put(tc,1);
            }
        }

        String minString = "";
        int start = 0;
        int end = 1;
        int s1_len = s1.length();
        int s2_len = s2.length();

        //存子串字符的统计个数
        HashMap<Character, Integer> map = new HashMap<>();
        //子串有效值     即 当count 值等于 目标子串的长度说明 子串是有效的
        int targetCount =0 ;
        //map.put( s1.charAt(start),1);  //我之前是 先加start  ,循环数组 每次都加end
        //和minWindow3 不同  start 必须要< end    即当目标字符只有1个字符是 使得start 不能越界 ，即start 不能和end 碰面
//        while(end<s1_len && start<end) {   // 先加start,  则 end 最大必须是  s1_len -1
        while(end<=s1_len && start<end) {

            //char c = s1.charAt(end);  //先加start
            // 每次值计算end前面 子串
            char c = s1.charAt(end-1);

            if(tmap.containsKey(c)){
                map.put(c, map.getOrDefault(c,0) + 1);
                //如果子串字符频次小于等于目标字符串频次 ，说明 子串贡献可以加
                // 即AABC  ABC    中 AABC  count 不会加1
                //OBCAA  AABC     中 OBCAA  的count会加1
                if(map.get(c) <= tmap.get(c)){
                    targetCount++;
                }
            }

            // 选取较小的子串_ 注意也要初始化子串
            // 最多56个字符遍历，所以是O（c） 的复杂度  即常数时间复杂度
            while (targetCount==s2_len && start < end) {
                if(end-start<minString.length()||minString.length()==0){
//                    minString = s1.substring(start, end+1);  //先加start  则覆盖子串末位索引取 end+1
                    minString = s1.substring(start, end);  //
                }

                // 左指针右移取更小的子串，需将map的原来最左端的字符count减一
                if(tmap.containsKey(s1.charAt(start))){
                    map.put(s1.charAt(start),map.get(s1.charAt(start))-1);

                    //滑过有效字符后，有效字符 count数减一，假设剩余count数小于目标子串字符count数，贡献值targetCount也要减一
                    if(map.get(s1.charAt(start)) < tmap.get(s1.charAt(start))){
                        targetCount --;
                    }
                }
                start++;
            }

            end++;

        }

        return minString;
    }

    /**
     * 在minWindow3的基础上优化 , (窗口 在minWindow3  时 是有最大范围的，在慢慢缩小的，在循环遍历整个数组的)
     *
     * 思路:
     * 对于窗口内的操作不是要循环遍历，而是随着窗口的移动，不考虑中间不变的元素，只考虑 进的元素和删的元素，减少窗口内部的算法复杂度
     * 即例如 求窗口最大最小值使用 大顶堆  和双队列
     *
     * 然后窗口的概念 是有 左右指针（已经定位了），  即窗口并不是固定大的（除非是固定窗口），  只需要 左右指针在循环整个数组后，得到结果即可。
     * 那么左右指针循环数组，只考虑窗口的进出元素，是效率最高的。
     *
     * 时间复杂度分析：O(2n) =  O（end） + O（start）
     *      对于end 的外层while 循环  它走了n步 是O(n)
     *      对于内层的start while 循环   它对于 整个 end 循环来说 也只走了 n步   即 时间复杂度是O(n)
     *          假如 end 是10   start 为5  => end  为11  start 为6
     *          end 从10=>11 时  start  不是 从 0=> n 的  是从 5=>6
     *          即内层循环 相对于整个 end 循环，最多走 n 步
     *
     *
     * @param s1
     * @param s2
     * @return
     */
    public static String minWindow4(String s1,String s2){

        //遍历目标子串的字符个数存入map中
        HashMap<Character, Integer> tmap = new HashMap<>();

        for (int i = 0; i < s2.length(); i++) {
            char tc = s2.charAt(i);
            if(tmap.containsKey(tc)){
                tmap.put(tc,tmap.get(tc)+1);
            }else {
                tmap.put(tc,1);
            }
        }

        String minString = "";
        int start = 0;
        int end = 1;
        int s1_len = s1.length();

        HashMap<Character, Integer> map = new HashMap<>();
        //map.put( s1.charAt(start),1);  //我之前是 先加start  ,循环数组 每次都加end
        //和minWindow3 不同  start 必须要< end    即当目标字符只有1个字符是 使得start 不能越界 ，即start 不能和end 碰面
//        while(end<s1_len && start<end) {   // 先加start,  则 end 最大必须是  s1_len -1
        while(end<=s1_len && start<end) {


                //char c = s1.charAt(end);  //先加start

                // 每次值计算end前面 子串
                char c = s1.charAt(end-1);

                if(tmap.containsKey(c)){
                    map.put(c, map.getOrDefault(c,0) + 1);
                }


            // 选取较小的子串_ 注意也要初始化子串
            // 最多56个字符遍历，所以是O（c） 的复杂度  即常数时间复杂度
            while (checkIsSubString(tmap, map) && start < end) {
                if(end-start<minString.length()||minString.length()==0){
//                    minString = s1.substring(start, end+1);  //先加start  则覆盖子串末位索引取 end+1
                    minString = s1.substring(start, end);  //
                }

                // 左指针右移取更小的子串，需将map的原来最左端的字符count减一
                if(tmap.containsKey(s1.charAt(start))){
                    map.put(s1.charAt(start),map.get(s1.charAt(start))-1);
                }

                start++;
            }

            end++;

        }

        return minString;
    }


    /**
     *
     * 在minWindow2的基础上优化，使用窗口（左右指针） 进行优化
     *
     * 在原来的得到的子串上 左右指针 按照长度渐小的方向移动
     *      首先 ADOBECODEBANC 中 ADOBEC 子串有 就不用在去判断 ADOBECO   因为长度小的子串更符合要求，减少遍历
     *
     *
     *      左右指针该如何移动？
     *      在找到符合的子串情况下
     *      EBANC 这种 左指针右移缩小 子串长度
     *
     *      遍历S1时
     *      ODEBAN 这种 右指针右移 找到符合子串
     *
     * 解题历程：
     * 这里  写代码的时候 把  minWindow2  的  for i 和 for j 加一起了,
     * 为什么 留这个 是因为  整个s1 要全部遍历完；
     * 但是实际上左右指针 的各自移动就能遍历整个数组 ，不需要fori 来多此一举
     *
     *
     * 时间复杂度 O(n^2)  while是n   fork 也是n  即 n^2
     *
     * @param s1
     * @param s2
     * @return
     */
    public static String minWindow3(String s1,String s2){

        //遍历目标子串的字符个数存入map中
        HashMap<Character, Integer> tmap = new HashMap<>();

        for (int i = 0; i < s2.length(); i++) {
            char tc = s2.charAt(i);
            if(tmap.containsKey(tc)){
                tmap.put(tc,tmap.get(tc)+1);
            }else {
                tmap.put(tc,1);
            }
        }

        String minString = "";
        int start = 0;
        int end = s2.length();
        int s1_len = s1.length();

        //start和end 有可能相遇   即 只有 S2作为 1个字符的时候，但是不影响  因为不满足条件时 end 会往后进位，start 只可能<=end
        while(end<=s1_len) {
            HashMap<Character, Integer> map = new HashMap<>();
            for (int k = start; k < end; k++) {
                char c = s1.charAt(k);
                if (map.containsKey(c)) {
                    map.put(c, map.get(c) + 1);
                } else {
                    map.put(c, 1);
                }
            }
            // 选取较小的子串_ 注意也要初始化子串
            // 最多56个字符遍历，所以是O（c） 的复杂度  即常数时间复杂度
            if (checkIsSubString(tmap, map)) {
                if(end-start<minString.length()||minString.length()==0){
                    minString = s1.substring(start, end);
                }
                start++;
            } else {
                end++;
            }
        }

        return minString;
    }


    /**
     * 暴力法
     * 思路：
     *      计算s1子串的个数，如果子串的每个字符的个数都大于等于 S2的每个字符个数，说明s1子串是可替代的子串
     *          在这基础之上找到s1子串长度最小的
     *
     *
     * 时间复杂度分析：O(n^3)
     * 空间复杂度：O(C)
     *
     * @param s1
     * @param s2
     * @return
     */
    public static String minWindow2(String s1,String s2){

        //遍历目标子串的字符个数存入map中
        HashMap<Character, Integer> tmap = new HashMap<>();

        for (int i = 0; i < s2.length(); i++) {
            char tc = s2.charAt(i);
            if(tmap.containsKey(tc)){
                tmap.put(tc,tmap.get(tc)+1);
            }else {
                tmap.put(tc,1);
            }
        }

        int s1_len = s1.length();
        int s2_len = s2.length();

        String minString = "";
        for (int i = 0; i < s1_len; i++) {

            //这里要注意边界条件
            // 下面都是 <j   即j取得最大值肯定是 s1_len
            for (int j = i+s2_len; j <= s1_len; j++) {

                HashMap<Character, Integer> map = new HashMap<>();

                for (int k = i; k <j ; k++) {

                    char c = s1.charAt(k);
                    if(map.containsKey(c)){
                        map.put(c,map.get(c)+1);
                    }else {
                        map.put(c,1);
                    }
                }
                // 选取较小的子串_ 注意也要初始化子串
                // 最多56个字符遍历，所以是O（c） 的复杂度  即常数时间复杂度
                if(checkIsSubString(tmap, map)&&((j-i)<minString.length() || minString.length()==0)){
                    minString = s1.substring(i,j);
                }
            }
        }

        return minString;
    }

    private static boolean checkIsSubString(HashMap<Character, Integer> tmap, HashMap<Character, Integer> map) {
        for (char character : tmap.keySet()) {
            //但凡 目标字符串的 某字符个数 大于 子串的某字符个数，子串就是不符合覆盖要求的
            if(tmap.get(character) > map.getOrDefault(character,0)){
                return false;
            }
        }
        return true;
    }


    /**
     *
     * 思路：
     *      先将s2 排序  得到 s2_
     *      对s1 进行拆分，每个子字符创都是 s2大小，且对 拆分的子串进行排序  如果  s2_  == 子串 则返回 s1 子串的下标
     *          假如 最小子串不符合目标，则扩大子串的长度重复上面的操作
     *
     * 时间复杂度和空间复杂度都很大
     *
     * @param s1
     * @param s2
     * @return
     */
    public static String minWindow1(String s1,String s2){
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
