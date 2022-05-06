package com.baowen.base.thread;

/**
 * @author mangguodong
 * @create 2021-04-03
 */
public class Base02_Lambda {

    public static void main(String[] args) {
        //高效编程多线程，使用lamada
        //拷贝小括号，写死右箭头，落地大括号
        Foo foo = (x,y)->{return x+y;};

        //接口的实现
        //foo是接口的的子类实现
        System.out.println("foo.sayxy(3,4) = " + foo.sayxy(3, 4));

        //接口既有抽象又有实现
        //default是接口实现方法
        System.out.println("foo.add(3,4) = " + foo.add(3, 4));

        //接口还有静态方法
        System.out.println("Foo.mv(13,2) = " + Foo.mv(13, 2));

        // ==> java越来越 面向接口编程

    }
}

interface Foo{

    //void say();
    int sayxy(int x,int y);

    default int add(int x,int y){
        System.out.println("add come");
        return x+y;
    }

    default int jian(int x,int y){
        return x+y;
    }

    public static int mv(int x, int y){

        return x*y;
    }

    public static int mv2(int x, int y){

        return x/y;
    }
}