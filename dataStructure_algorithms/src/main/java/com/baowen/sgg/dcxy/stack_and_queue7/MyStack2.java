package com.baowen.sgg.dcxy.stack_and_queue7;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author mangguodong
 * @create 2022-07-20
 */
public class MyStack2<T> {

    public static void main(String[] args) {
        MyStack2<Integer> m = new MyStack2<>();

        m.push(1);
        m.push(2);
        m.push(3);
        m.push(4);
        m.push(5);

        System.out.println(m.pop());
        System.out.println(m.top());

    }

    /**
     *     思路:使用单队列实现栈即
     *     1234 -> q1
     *     5来了怎么办  记录Q1 的长度 4
     *     5先放到q1里面,将q1出队4次，并将出队的元素放入5后面 =》 54321
     *
     *
     *
     */

    Queue<T> q1;

    public MyStack2() {
        q1 = new LinkedList<>();
    }

    public void push(T t){

        int size = q1.size();

        q1.offer(t);
        for (int i = 0; i < size; i++) {
            q1.offer(q1.poll());
        }
    }

    public T pop(){
        return q1.poll();
    }

    public T top(){
        return q1.peek();
    }

    public boolean Empty(){
        return q1.isEmpty();
    }

}
