package by.kotliarov.banklist.dao.impl;

import by.kotliarov.banklist.Account;
import by.kotliarov.banklist.User;
import by.kotliarov.banklist.connect.ConnectorJdbc;
import by.kotliarov.banklist.dao.DaoDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBDaoImpl implements DaoDB {
    private static DBDaoImpl dbDaoImpl = null;

    @Override
    public User read(int id) {
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
    public User getRichestUser() {
        User user = new User();
        Account account = new Account();
        String sql = "SELECT * FROM banklist.user INNER JOIN banklist.account ON user.userid = account.userid WHERE account = (SELECT MAX(account.account) FROM banklist.account)";
        ConnectorJdbc connection = new ConnectorJdbc();
        try {
            Statement statement = connection.getConnection().createStatement();
            //statement.executeQuery(sql);

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setSureName(resultSet.getString(3));
                account.setAccountid(resultSet.getInt(4));
                account.setAccount(resultSet.getInt(5));
                account.setUserid(resultSet.getInt(6));
                user.setAccount(account);
            }
            connection.getConnection().close();
        } catch (Exception e) {
        }
        return user;
    }

    @Override
    public int sumAccount() {
        String sql = "SELECT SUM(account) FROM banklist.account";
        ConnectorJdbc connection = new ConnectorJdbc();
        int sum = 0;
        try {
            Statement statement = connection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {

                sum = resultSet.getInt(1);
            }
            connection.getConnection().close();
        } catch (Exception e) {
        }
        return sum;
    }

    @Override
    public List<Account> readAllAccounts() throws Exception {

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



