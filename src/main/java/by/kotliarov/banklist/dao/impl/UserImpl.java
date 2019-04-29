package by.kotliarov.banklist.dao.impl;

import by.kotliarov.banklist.Account;
import by.kotliarov.banklist.User;
import by.kotliarov.banklist.connect.ConnectorJdbc;
import by.kotliarov.banklist.dao.UserDBDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserImpl implements UserDBDao {
    private static UserImpl userImpl = null;

    @Override
    public void create(User user) {
        String sql = "INSERT INTO banklist.user(userid, name,sureName) VALUES (?,?,?)";
        ConnectorJdbc connection = new ConnectorJdbc();

        try (PreparedStatement pstm = connection.getConnection().prepareStatement(sql)) {

            pstm.setInt(1, user.getId());
            pstm.setString(2, user.getName());
            pstm.setString(3, user.getSureName());
            pstm.executeUpdate();
            connection.getConnection().close();

        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public User reader(int id) {
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
    public void update(User user, int id) {
        String sql = "UPDATE banklist.user SET userid = ? ,  name = ? , sureName = ? WHERE userid = ?";
        ConnectorJdbc connection = new ConnectorJdbc();

        try (PreparedStatement pstm = connection.getConnection().prepareStatement(sql)) {

            pstm.setInt(1, user.getId());
            pstm.setString(2, user.getName());
            pstm.setString(3, user.getSureName());
            pstm.setInt(1, id);
            pstm.executeUpdate();
            connection.getConnection().close();
        } catch (Exception e) {
            e.getMessage();
        }

    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM banklist.user WHERE userid = ?";
        ConnectorJdbc connection = new ConnectorJdbc();
        try (PreparedStatement pstm = connection.getConnection().prepareStatement(sql)) {
            pstm.setInt(1, id);
            pstm.executeUpdate();
            connection.getConnection().close();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public User getRichestUser() {
        User user = new User();
        Account account = new Account();
        String sql = "SELECT * FROM banklist.user INNER JOIN banklist.account ON user.userid = account.userid WHERE account = (SELECT MAX(account.account) FROM banklist.account)";
        ConnectorJdbc connection = new ConnectorJdbc();
        try {
            Statement statement = connection.getConnection().createStatement();


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
    public List<User> readAll()  {

        String sql = "SELECT * FROM banklist.user";
        List<User> users = null;
        ConnectorJdbc connection = new ConnectorJdbc();
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                if (users == null) {
                    users = new ArrayList<>();
                }
                int usertid = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String sureName = resultSet.getString(3);
                User user = new User();
                user.setId(usertid);
                user.setName(name);
                user.setSureName(sureName);
                users.add(user);
            }
            connection.getConnection().close();
        } catch (Exception e) {
            e.getMessage();
        }
        return users;
    }

    public static UserImpl getInstance() {
        if (userImpl == null)
            userImpl = new UserImpl();

        return userImpl;
    }
}



