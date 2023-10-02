package app.service;

import app.model.User;

import java.util.List;

public interface UserService {
    void create(User user);
    List<User> read();
    void update(String name, String lastName, int age, Long id);
    void delete(Long id);

    User getByID(Long id);
}
