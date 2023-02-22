package web.dao;

import web.entity.User;

import java.util.List;

public interface UserDAO {
    void add(User user);
    void delete(int id);
    User read(int id);
    List<User> getAllUsers();
    void update(int id, User user);
}
