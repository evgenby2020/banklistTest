package by.kotliarov.banklist.dao;

import by.kotliarov.banklist.Account;
import by.kotliarov.banklist.User;

import java.util.List;

public interface DaoDB {

    User read(int id);
    List<Account> readAllAccounts()throws Exception;
    int sumAccount();
    User getRichestUser();
}
