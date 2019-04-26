package by.kotliarov.banklist.dao.impl;

import by.kotliarov.banklist.Account;
import by.kotliarov.banklist.User;
import by.kotliarov.banklist.connect.ConnectorJdbc;
import by.kotliarov.banklist.dao.DaoDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DBDaoImpl implements DaoDB {
    private static DBDaoImpl dbDaoImpl = null;
    @Override
    public  User reade(int id) {
        ConnectorJdbc connection = new ConnectorJdbc();
        User user = new User();
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement("SELECT * FROM banklist.user WHERE userid=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet2 = preparedStatement.executeQuery();
            while (resultSet2.next()) {
                user.setId(resultSet2.getInt(1));
                user.setName(resultSet2.getString(2));
                user.setSureName(resultSet2.getString(3));
            }
            connection.getConnection().close();
        } catch (Exception e) {
            e.getMessage();
        }
        return user;
    }

    @Override
    public List<Account> readAll() throws Exception {

        String sql = "SELECT * FROM banklist.account";
        List<Account> accounts = null;
        ConnectorJdbc connection = new ConnectorJdbc();
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                if (accounts == null) {
                    accounts = new ArrayList<>();
                }
                int accountid = resultSet.getInt(1);
                int account = resultSet.getInt(2);
                int userid = resultSet.getInt(3);
                Account account1 = new Account();
                account1.setAccount(account);
                account1.setAccountid(accountid);
                account1.setUserid(userid);
                accounts.add(account1);
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            connection.getConnection().close();
        }
        return accounts;
    }

    public static DBDaoImpl getInstance() {
        if (dbDaoImpl == null)
            dbDaoImpl = new DBDaoImpl();

        return dbDaoImpl;
    }
}



