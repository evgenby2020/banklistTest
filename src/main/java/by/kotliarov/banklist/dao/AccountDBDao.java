package by.kotliarov.banklist.dao;

import by.kotliarov.banklist.Account;


import java.util.List;

public interface AccountDBDao {

    void create(Account account);

    Account reader(int id);

    void update(Account account, int id);

    void delete(int id);

    List<Account> readAll();

    int sumAccount();
}
