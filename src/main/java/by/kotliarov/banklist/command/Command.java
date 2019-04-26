package by.kotliarov.banklist.command;

import by.kotliarov.banklist.Account;
import by.kotliarov.banklist.User;
import by.kotliarov.banklist.dao.DaoDB;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface Command {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
