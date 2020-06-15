package com.example.demo2.SpringContainer;

public interface AccountService {

    public  void transferMoney(long sourceAccountId, long targetAccountId ,double amount);

    public  void depositMoney(long accountid ,double amount);

    public  Account getAccount(long accountid);
}
