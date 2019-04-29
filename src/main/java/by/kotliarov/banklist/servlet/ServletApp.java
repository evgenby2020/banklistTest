package by.kotliarov.banklist.servlet;

import by.kotliarov.banklist.command.Command;
import by.kotliarov.banklist.command.GetAllSumCommand;
import by.kotliarov.banklist.command.RichestUserCommand;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/bank")
public class ServletApp extends HttpServlet {
    private Map<String, Command> commandMap = new HashMap<String, Command>();
    private final String USER = "user";
    private final String ACCOUNT = "account";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    public void init()  {
        commandMap.put(USER, new RichestUserCommand());
        commandMap.put(ACCOUNT, new GetAllSumCommand());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = commandMap.get(req.getParameter("flag"));
        try {
            command.execute(req, resp);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
