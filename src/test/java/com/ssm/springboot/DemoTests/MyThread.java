package com.ssm.springboot.DemoTests;

/**
 * @author zhaohf
 * @date 2020/3/4 15:42
 */
public class MyThread extends Thread {

    public MyThread(String name) {
        super(name);
    }

    static int tick=20;

    static MyThread thread = new MyThread("");

    @Override
    public void run() {
        while (tick>0){
            synchronized (thread) {
                if(tick>0){
                    System.out.println(this.getName()+"说：我卖出了第"+tick+"张票");
                    tick--;
                }else {
                    System.out.println(this.getName()+"说：抱歉，票买完了！");
                }
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class main{
        public static void main(String[] args) {
            MyThread t1=new MyThread("窗口1");
            MyThread t2=new MyThread("窗口2");
            MyThread t3=new MyThread("窗口3");
            t1.start();
            t2.start();
            t3.start();
        }
    }
}
