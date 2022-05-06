package com.baowen.base.thread;

/**
 * @author mangguodong
 * @create
 */

/**
 * ①基本模板--线程操作资源类
 * ②线程通信--判断、干活、通知
 * ③线程通信，为了防止虚假唤醒，判断要用while，不能用if
 * ④线程精准唤醒--注意标志位的修改和定位
 */
public class Base00_ThreadTongBuTest {

    public static void main(String[] args) {


        Counter c1 = new Counter();

        Thread t1= new Thread(c1);
        Thread t2= new Thread(c1);
        t1.setName("线程1");
        t2.setName("线程2");
        t1.start();
        t2.start();
    }

}

class Counter implements Runnable{

    private  int counter = 200;
    @Override
    public void run() {
        for (int i = 0; i <50 ; i++) {
            //不加锁有线程同步问题
            //a、b线程同时 操作counter   本来a-2 得到198 ，应该是 打印198，a还未打印之前，b-2 导致 ，打印196，a与预期的结果不对，就是 同步问题
            synchronized (Counter.class){
                counter-=2;
                System.out.println(Thread.currentThread().getName()+" "+counter);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }
    }
}
