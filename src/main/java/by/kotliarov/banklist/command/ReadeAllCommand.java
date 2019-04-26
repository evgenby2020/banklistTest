package by.kotliarov.banklist.command;

import by.kotliarov.banklist.Account;
import by.kotliarov.banklist.dao.DaoDB;
import by.kotliarov.banklist.dao.impl.DBDaoImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ReadeAllCommand implements Command {
    private DaoDB dbDao = DBDaoImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Account> accountList = dbDao.readAll();
        int sum = 0;
        for (Account account : accountList) {
            sum += account.getAccount();
        }
        request.setAttribute("account", sum);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
