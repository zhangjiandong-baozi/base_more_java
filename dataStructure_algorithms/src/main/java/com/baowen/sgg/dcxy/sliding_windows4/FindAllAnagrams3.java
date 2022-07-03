package com.baowen.sgg.dcxy.sliding_windows4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 438
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 *
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 *  示例 2:
 *
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 *  
 *
 * 提示:
 *
 * 1 <= s.length, p.length <= 3 * 104
 * s 和 p 仅包含小写字母
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-all-anagrams-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author mangguodong
 * @create 2022-07-03
 */
public class FindAllAnagrams3 {

    public static void main(String[] args) {
        String s = "cbaebabacd";

        String p = "abc";


        System.out.println("FindAllAnagrams(s, p) = " + FindAllAnagrams(s, p));
    }

    /**
     * 滑动窗口(双指针法)
     *
     *窗口该怎么移动得到想要的结果？  start++ 来抵消end++  保证s的子串还是 p的异位词
     *
     * 时间复杂度：
     *      外层while 是 1到n  即 O(n)
     *      内层循环相对于整个外层循环   start 最多走 0到n-1
     *
     *      所以整个时间复杂度是 O(n) + O(n)  即 2O(n)  即O(n)
     *
     * @param s
     * @param p
     * @return
     */
    public static List<Integer> FindAllAnagrams(String s,String p){

        int pLen = p.length();
        int[] pcount = new int[26];

        for (int i = 0; i < p.length(); i++) {
            pcount[p.charAt(i)-'a']++;
        }

        ArrayList<Integer> result = new ArrayList<>();

        // 定义双指针，指向窗口的起始和结束位置
        int start = 0,end = 1;
        // 统计子串中所有字符出现频次的数组
        int[] scount = new int[26];
        // 移动指针，总是截取字符出现频次全部小于等于p中字符频次的子串
        while (end<=s.length()){

            char c = s.charAt(end - 1);

            scount[c-'a']++;

            //start++ 来抵消end++  保证s的子串还是 p的异位词
            while(scount[c-'a']>pcount[c-'a'] && start < end){
                char removeChar = s.charAt(start);
                scount[removeChar-'a']--;
                start++;
            }

            // 如果当前子串长度等于p的长度，那么就是一个字母异位词
            if(end-start==pLen){
                result.add(start);
            }

            end++;
        }


        return result;
    }


    /**
     * 暴力法计数_老师
     *思路
     *
     * 算法复杂度 是 O(n-p)*O(p)   即 O(np-p^2)
     *
     * @param s
     * @param p
     * @return
     */
    public static List<Integer> FindAllAnagrams4(String s,String p){

        int pLen = p.length();
        int[] pcount = new int[26];

        for (int i = 0; i < p.length(); i++) {
            pcount[p.charAt(i)-'a']++;
        }

        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < s.length()-pLen; i++) {

            int[] scount = new int[26];
            //判断当前子串是否为p的字母异位词
            boolean isMatched = true;

            for (int k = i; k <i+pLen ; k++) {

                scount[s.charAt(k)-'a']++;

                // 判断当前字符频次，如果超过了p中的频次，就一定不符合要求
                // 因为p 是固定长度的字符创
                // 假如 p= abc   s子串有adc 时  s的d,1    >  p的 d,0
                 if (scount[s.charAt(k) - 'a'] > pcount[s.charAt(k) - 'a']){
                    isMatched = false;
                    break;
                }

            }
            if (isMatched) {
                result.add(i);
            }

        }

        return result;
    }

    /**
     *
     * 滑动窗口(自己)
     *      先将p的字符个数存入pmap中
     * 即左右指针  左指针 和右指针好像是同时移动的，
     * 每滑动一次   进位字符 在map 中的计算 +1， 滑位字符 在map 中计数减1 ,与pmap 中的字符比较  ,字符计算相同则 返回s下标
     *
     *时间复杂度是  O(n) * O(p)
     * @param s
     * @param p
     * @return
     */
    public static List<Integer> FindAllAnagrams3(String s,String p){

        HashMap<Character, Integer> pmap = new HashMap<>();
        int pLen = p.length();
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            pmap.put(c,pmap.getOrDefault(c,0)+1);
        }

        HashMap<Character, Integer> map = new HashMap<>();
        //初始化0到plen-1
        for (int i = 0; i <pLen ; i++) {
            char c = s.charAt(i);
            map.put(c,map.getOrDefault(c,0)+1);
        }
        if(check(pmap,map)){
            list.add(0);
        }
        //遍历 end 至 末尾
        for (int i = pLen; i < s.length(); i++) {
            char end = s.charAt(i);
            char start = s.charAt(i-pLen);

            map.put(end,map.getOrDefault(end,0)+1);
            map.put(start,map.get(start)-1);

            if(check(pmap,map)){
                list.add(i-pLen+1);
            }

        }

        return list;
    }



    /**
     * 暴力法_计数(自己)
     * 思路:
     *      计算p字符的个数放在map中,循环s,如果s的子串个数和map相同，则返回下标
     *
     *      时间复杂度是 O(n) * O(2p)
     *
     *
     * @param s
     * @param p
     * @return
     */
    public static List<Integer> FindAllAnagrams2(String s,String p){

        HashMap<Character, Integer> pmap = new HashMap<>();
        int pLen = p.length();
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            pmap.put(c,pmap.getOrDefault(c,0)+1);
        }

        for (int i = 0; i < s.length()-pLen; i++) {
            int j = i+pLen;
            HashMap<Character, Integer> map = new HashMap<>();
            for (int k = i; k < j; k++) {
                char c = s.charAt(k);
                map.put(c,map.getOrDefault(c,0)+1);
            }

             if(check(pmap,map)){
                 list.add(i);
             }

        }

        return list;
    }

    private static boolean check(HashMap<Character, Integer> pmap, HashMap<Character, Integer> map) {

        for (char character : pmap.keySet()) {

            if(!pmap.get(character).equals(map.get(character))){
                return false;
            }
        }
        return true;
    }

    /**
     *
     * 暴力法_排序(自己)
     * 思路：
     *      将p排序得到p_,循环s  在s中得到的子串 也排序，  如果匹配 返回相应下标
     *
     * 时间复杂度： for循环*排序*截取  O(n)*O(p*logp)*O(p)  时间复杂度是 O(n)
     *
     * @param s
     * @param p
     * @return
     */
    public static List<Integer> FindAllAnagrams1(String s,String p){

        String p_ = MinWindowSubstring2.rankString(p);
        int pLen = p.length();
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i <= s.length()-pLen; i++) {
            int j = i+pLen;
            if(p_.equals(MinWindowSubstring2.rankString(s.substring(i,j)))){
                list.add(i);
            }
        }

        return list;
    }



}
