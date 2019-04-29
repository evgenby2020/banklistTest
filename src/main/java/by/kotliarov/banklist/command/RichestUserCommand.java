package by.kotliarov.banklist.command;


import by.kotliarov.banklist.User;
import by.kotliarov.banklist.dao.UserDBDao;
import by.kotliarov.banklist.dao.impl.UserImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RichestUserCommand implements Command {
    private User user;
    private UserDBDao userDBDao = UserImpl.getInstance();


    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        user = userDBDao.getRichestUser();
        request.setAttribute("userid", user.getName() + " " + user.getSureName() + " (" + user.getId() + ")");
        request.setAttribute("account", user.getAccount().getAccount());
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}

