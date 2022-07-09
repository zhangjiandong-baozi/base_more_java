package com.baowen.sgg.dcxy.linklist5;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author mangguodong
 * @create 2022-07-04
 */
public class TestLinkList {

    public static void main(String[] args) {

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = null;

        Node curnode = node1;
        while (curnode!=null){
            System.out.print(curnode.val+"->");
            curnode = curnode.next;
        }

        //1->2->3->4->5->



    }

    public static void printList(Node head){
        Node currnode = head;
        while (currnode!=null){
            System.out.print(currnode.val+"->");
            currnode = currnode.next;
        }
    }

    /**
     *文件的递归删除
     * 递归问题 可以看成  ：一个问题可以分解为小问题和子问题，小问题解决，子问题递归
     * 文件目录的删除可以看成  ： 一个目录是子文件和子目录的结合，子文件可以直接删除，子目录递归，等子文件和子目录都删除了在删除自己这个空文件夹
     * @param file
     */
    public void deleteDir(File file){
        if(file == null){
            System.out.println("您传入的参数为空");
            return;
        }
        File[] files = file.listFiles();//列出子目录和子文件
        for (int i = 0; i < files.length; i++) {
            if(files[i].isFile()){//是子文件直接删除 即小问题直接解决
                files[i].delete();
            }else{
                deleteDir(files[i]); //是子目录直接递归删除 即子问题直接递归
            }
        }

        file.delete();//等子文件和子目录都删除了，在删除自己这个空文件夹
    }
    /**
     文件的复制
     */
    @Test
    public void test2(){
        File in = new File("D:\\lscf\\foplscf");
        File out = new File("D:\\lscf\\lscf20200819");
        copyDir(in,out);
    }
    /**
     *递归问题，可以分解成一个小问题和子问题，小问题直接解决，子问题递归
     * 小问题是遇到文件就创建io流复制内容，子问题是下个文件夹也是同样的操作
     * @param infile 要复制的源文件夹
     * @param outfile 需要复制到哪个文件夹下
     */
    public void copyDir(File infile,File outfile){
        //一上来就创建文件夹
        outfile.mkdir();
        File[] files = infile.listFiles();
        if(files == null){ //没有权限访问的文件直接不进行操作
            System.out.println(infile+"不允许访问");
            return;
        }
        for (int i = 0; i < files.length; i++) {
            String out = outfile +"\\"+ files[i].getName();
            if(files[i].isDirectory()){
                copyDir(files[i],new File(out));//子问题递归  如果是目录, 直接让子目录递归到目标的子目录中
            }else {
                copyFile(files[i],new File(out));//小问题解决  如果是文件创建io流进行复制
            }
        }
    }
    public void copyFile(File infile,File outFile){

        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            System.out.println("复制文件,从"+infile.getAbsolutePath()+" 到 "+outFile.getAbsolutePath());
            fis = new FileInputStream(infile);
            fos = new FileOutputStream(outFile);
            byte[] buf  = new byte[8192];
            int count;
            while ((count = fis.read(buf))!=-1){
                fos.write(buf,0,count);
                fos.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(fos!=null){}
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
