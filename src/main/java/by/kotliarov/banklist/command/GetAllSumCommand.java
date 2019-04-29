package by.kotliarov.banklist.command;



import by.kotliarov.banklist.dao.AccountDBDao;
import by.kotliarov.banklist.dao.impl.AccountImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GetAllSumCommand implements Command {
    private AccountDBDao accountDBDao  = AccountImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int sum = accountDBDao.sumAccount();
        request.setAttribute("account", sum);
        request.setAttribute("userid","");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
