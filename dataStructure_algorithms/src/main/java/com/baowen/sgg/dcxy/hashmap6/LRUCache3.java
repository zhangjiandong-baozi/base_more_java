package com.baowen.sgg.dcxy.hashmap6;

import java.util.HashMap;

/**
 *
 * 自定义双向链表+hashmap 实现LRU(最近最少使用)
 *
 * 思路：
 *      tail  head
 *      把每次使用（get和put）的节点 放在 链表的尾部
 *      put方法
 *          如果是新增，节点放在链表尾部，
 *           如果容量达到上限删除头元素
 *          如果是修改，移动节点值尾部
 *
 * @author mangguodong
 * @create 2022-07-09
 */
public class LRUCache3 {

    public static void main(String[] args) {
        LRUCache3 lRUCache = new LRUCache3(2);
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

    //定义双向链表节点
    class Node{
        int key;
        int value;
        Node next;
        Node prev;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public Node() {
        }
    }
    //缓存的容量
    private int capacity;
    //缓存目前大小
    private int size;

    private HashMap<Integer,Node> map;

    private Node tail ,head;



    public LRUCache3(int capacity) {
        this.capacity = capacity;
        size= 0;
        map= new HashMap<Integer,Node>();

        //定义链表的头尾的哨兵节点方便链表的操作
        head = new Node();
        tail = new Node();

        // 每次活跃的元素就放在 链表末尾
        head.next = head;
        tail.prev = head;
    }

    /**
     * 获取元素的方法
     * 获取一次需要将节点放入尾部
     *
     * @param key
     */
    public int get(int key){

        if(map.get(key)==null)return -1;
        Node node = map.get(key);
        //将节点移动到尾部
        movetoTail(node);
        return node.value;
    }

    /**
     *
     * 如果是新增
     *      未超过容量直接加到尾部
     *      超过容量移除头节点，在将此节点加到尾部
     *
     * 如果是修改
     *      修改后将节点移动到尾部
     *
     * @param key
     * @param value
     */
    public void put(int key,int value){
        Node node = map.get(key);
        if(node==null){
            Node newNode = new Node(key, value);
            //超出容量 移除头结点
            System.out.println("key= "+key+" size="+size+" capacity"+capacity);
            map.put(key,newNode);
            //将新元素加到链表末尾
            addtoTail(newNode);
            size++;

            //之前我把判断放在put之前，其实没必要，因为put的操作总是要做
            if(size>capacity){

                //map中移除节点
                System.out.println();
                //链表中移除头节点
                Node n= removeHead();
                map.remove(n.key);
                size--;
            }

        }else {
            node.value = value;
            //移动节点到尾部
            movetoTail(node);
        }

    }


    public void movetoTail(Node newNode){

        //删除自身在链表的位置，并加到链表的尾部
        removeNode(newNode);
        addtoTail(newNode);

    }

    //将节点加到链表尾部
    public void addtoTail(Node newNode){
        newNode.next = tail;
        newNode.prev = tail.prev;
        tail.prev.next=newNode;
        tail.prev=newNode;

    }

    public void removeNode(Node node){

        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // 删除头节点
    private Node removeHead(){
        Node realHead = head.next;
        removeNode(realHead);
        return realHead;
    }



}
