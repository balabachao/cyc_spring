package com.example.demo2.SpringContainer;

public interface AccountDao {
    public  void insert(Account account);
    public  void update(Account account);
    public  void delete(Account account);
    public Account find(long account);
}
