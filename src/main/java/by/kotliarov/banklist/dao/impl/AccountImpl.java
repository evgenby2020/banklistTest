package by.kotliarov.banklist.dao.impl;




import by.kotliarov.banklist.Account;
import by.kotliarov.banklist.connect.ConnectorJdbc;
import by.kotliarov.banklist.dao.AccountDBDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

public class AccountImpl implements AccountDBDao {
    private static AccountImpl accountImpl = null;
    private Account account;

    @Override
    public void create(Account account) {
        String sql = "INSERT INTO banklist.account(accountid, account,userid) VALUES (?,?,?)";
        ConnectorJdbc connection = new ConnectorJdbc();

        try (PreparedStatement pstm = connection.getConnection().prepareStatement(sql)) {

            pstm.setInt(1, account.getAccountid());
            pstm.setInt(2, account.getAccount());
            pstm.setInt(3, account.getUserid());
            pstm.executeUpdate();
            connection.getConnection().close();

        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public Account reader(int id) {

        String sql = "SELECT * FROM banklist.account WHERE accountid = ?)";
        ConnectorJdbc connection = new ConnectorJdbc();
        account = new Account();
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet2 = preparedStatement.executeQuery();
            while (resultSet2.next()) {
                account.setAccountid(resultSet2.getInt(1));
                account.setAccount(resultSet2.getInt(2));
                account.setUserid(resultSet2.getInt(3));
            }
            connection.getConnection().close();
        } catch (Exception e) {
            e.getMessage();
        }

        return account;

    }

    @Override
    public void update(Account account, int id) {
        String sql = "UPDATE banklist.account SET accountid = ? ,  account = ? , userid = ? WHERE accountid = ?";
        ConnectorJdbc connection = new ConnectorJdbc();

        try (PreparedStatement pstm = connection.getConnection().prepareStatement(sql)) {

            pstm.setInt(1, account.getAccountid());
            pstm.setInt(2, account.getAccount());
            pstm.setInt(3, account.getUserid());
            pstm.setInt(3, id);
            pstm.executeUpdate();
            connection.getConnection().close();
        } catch (Exception e) {
            e.getMessage();
        }

    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM banklist.account WHERE accountid = ?";
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
    public List<Account> readAll() {
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
            connection.getConnection().close();
        } catch (Exception e) {
            e.getMessage();
        }
        return accounts;
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

    public static AccountImpl getInstance() {
        if (accountImpl == null)
            accountImpl = new AccountImpl();

        return accountImpl;
    }
}
