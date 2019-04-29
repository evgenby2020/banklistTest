package by.kotliarov.banklist.command;

import by.kotliarov.banklist.dao.impl.DBDaoImpl;
import by.kotliarov.banklist.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RichestUserCommand implements Command {
    private DBDaoImpl dbDao = DBDaoImpl.getInstance();
    private User user;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        user = dbDao.getRichestUser();
        request.setAttribute("userid", user.getName() + " " + user.getSureName() + " (" + user.getId() + ")");
        request.setAttribute("account", user.getAccount().getAccount());
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}

