package com.example.demo2.SpringContainer;

public class AccountServiceImpl implements AccountService {
    private AccountDao accountDao;
    public  void setAccountDao(AccountDao accountDao){
        this.accountDao =accountDao;
    }

    @Override
    public void transferMoney(long sourceAccountId, long targetAccountId, double amount) {
        Account sourceAccount = accountDao.find(sourceAccountId);
        Account targetAccount = accountDao.find(targetAccountId);
        sourceAccount.setBalance(sourceAccount.getBalance()-amount);
        targetAccount.setBalance(targetAccount.getBalance()+amount);
        accountDao.update(sourceAccount);
        accountDao.update(targetAccount);
    }

    @Override
    public void depositMoney(long accountid, double amount) {
        Account account = accountDao.find(accountid);
        account.setBalance(account.getBalance()-amount);
        accountDao.update(account);
    }

    @Override
    public Account getAccount(long accountid) {
        return accountDao.find(accountid);
    }
}
