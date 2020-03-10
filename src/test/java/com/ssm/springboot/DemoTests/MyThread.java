package com.ssm.springboot.DemoTests;

/**
 * @author zhaohf
 * @date 2020/3/4 15:42
 */
public class MyThread {


    public static void main(String[] args) {
        Thread.currentThread().setName("Main");
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0 ; i < 10 ; i++) {
                    System.out.println(Thread.currentThread().getName()+i);
                }
            }
        });
        thread.start();
    }
}
