package by.kotliarov.banklist.dao;


import by.kotliarov.banklist.User;

import java.util.List;

public interface UserDBDao {
    void create(User user);

    User reader(int id);

    void update(User user, int id);

    void delete(int id);

    List<User> readAll();

    User getRichestUser();


}
