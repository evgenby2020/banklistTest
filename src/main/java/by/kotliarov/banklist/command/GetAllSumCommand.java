package by.kotliarov.banklist.command;

import by.kotliarov.banklist.Account;
import by.kotliarov.banklist.dao.DaoDB;
import by.kotliarov.banklist.dao.impl.DBDaoImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GetAllSumCommand implements Command {
    private DaoDB dbDao = DBDaoImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int sum = dbDao.sumAccount();
        request.setAttribute("account", sum);
        request.setAttribute("userid","");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
