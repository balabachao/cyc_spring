package com.example.demo2.SpringContainer;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainFuntion {
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfiguration.class);

        AccountService accountService= applicationContext.getBean("accountService",AccountService.class);
        System.out.println("开始转账前");
        Thread.sleep(2000);
        System.out.println("账户"+accountService.getAccount(1).getOwerName()+"的余额"+accountService.getAccount(1).getBalance()+"元");
        Thread.sleep(2000);
        System.out.println("账户"+accountService.getAccount(3).getOwerName()+"的余额"+accountService.getAccount(3).getBalance()+"元");
        Thread.sleep(2000);
        System.out.println("正在开始转账");
        Thread.sleep(2000);
        accountService.transferMoney(1,3,5.0);
        System.out.println("转账结束");
        Thread.sleep(1000);
        System.out.println("账户"+accountService.getAccount(1).getOwerName()+"的余额"+accountService.getAccount(1).getBalance()+"元");
        Thread.sleep(2000);
        System.out.println("账户"+accountService.getAccount(3).getOwerName()+"的余额"+accountService.getAccount(3).getBalance()+"元");

    }
}
