package com.example.demo2.SpringContainer;

import java.util.HashMap;
import java.util.Map;

public class AccountDaoInMemoryImpl implements AccountDao {
    private Map<Long, Account> accountMap = new HashMap<>();

    {
        Account accountA = new Account();
        accountA.setId(1L);
        accountA.setOwerName("Zhang3");
        accountA.setBalance(10.0);

        Account accountB = new Account();
        accountB.setId(3L);
        accountB.setOwerName("Li4");
        accountB.setBalance(20.0);
        accountMap.put(accountA.getId(), accountA);
        accountMap.put(accountB.getId(), accountB);
    }

    @Override
    public void insert(Account account) {

    }

    @Override
    public void update(Account account) {
        accountMap.put(account.getId(),account);
    }

    @Override
    public void delete(Account account) {

    }

    @Override
    public Account find(long account) {
        return accountMap.get(account);
    }
}
