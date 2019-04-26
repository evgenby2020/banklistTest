package by.kotliarov.banklist.dao;

import by.kotliarov.banklist.Account;
import by.kotliarov.banklist.User;

import java.util.List;

public interface DaoDB {

    User reade(int id);
    List<Account> readAll()throws Exception;
}
