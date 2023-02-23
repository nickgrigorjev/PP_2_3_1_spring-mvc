package web.service;

import web.entity.User;

import java.util.List;

public interface UserService {
    void create(User user);
    void delete(int id);
    User show(int id);
    List<User> getAllUsers();
    void update(int id, User user);
}
