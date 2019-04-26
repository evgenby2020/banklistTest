package by.kotliarov.banklist.command;
import by.kotliarov.banklist.dao.impl.DBDaoImpl;

import by.kotliarov.banklist.Account;
import by.kotliarov.banklist.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ReadeCommand implements Command {
    private DBDaoImpl dbDao = DBDaoImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {


    }


}

