package web.dao;

import web.entity.User;

import java.util.List;

public interface UserDAO {
    void create(User user);
    void delete(int id);
    User show(int id);
    List<User> getAllUsers();
    void update(int id, User user);
}
