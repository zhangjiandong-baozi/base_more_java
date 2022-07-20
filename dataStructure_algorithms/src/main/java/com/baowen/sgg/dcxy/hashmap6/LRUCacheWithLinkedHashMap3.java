package com.baowen.sgg.dcxy.hashmap6;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 使用linkhashmap实现一个LRU
 *
 * @author mangguodong
 * @create 2022-07-09
 */
public class LRUCacheWithLinkedHashMap3 extends LinkedHashMap<Integer,Integer> {

    public static void main(String[] args) {
        LRUCacheWithLinkedHashMap3 lRUCache = new LRUCacheWithLinkedHashMap3(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        System.out.println(lRUCache.get(1));   // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lRUCache.get(1));     // 返回 -1 (未找到)
        System.out.println(lRUCache.get(3));     // 返回 3
        System.out.println(lRUCache.get(4));     // 返回 4

    }

    private int capacity;

    public LRUCacheWithLinkedHashMap3(int capacity) {
        //加载因子  0.75  即超过0.75扩容   LRU 不需要扩容
        //accessOrder  活跃的元素是否重新排序？ true
        super(capacity,0.75f,true);
        this.capacity = capacity;
    }

    @Override
    public Integer get(Object key) {
        if(super.get(key)==null) return -1;
        return super.get(key);
    }

    @Override
    public Integer put(Integer key, Integer value) {
        return super.put(key, value);
    }

    // 大于容量才删除 首次入队元素
    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size()>capacity;
    }
}
