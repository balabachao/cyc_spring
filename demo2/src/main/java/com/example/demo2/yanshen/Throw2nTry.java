package com.example.demo2.yanshen;

public class Throw2nTry {

    public static void main(String[] args) throws Exception {
        nuException();
    }


    public static void numException(){
        int[] a = {1,0,9};

        for (int i = 0; i <a.length ; i++) {
            try {
                System.out.println("计算结果是："+90/a[i]);
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println("捕获了一个异常");
                e.printStackTrace();
            }
        }
     }
    public static void nuException()throws Exception{
        int[] a = {1,0,9,2};

        for (int i = 0; i <a.length ; i++) {
                System.out.println("计算结果是："+90/a[i]);
                Thread.sleep(2000);
            }
        }

}
