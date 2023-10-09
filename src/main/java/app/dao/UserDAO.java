package app.dao;

import app.model.User;

import java.util.List;

public interface UserDAO {
    void create(User user);
    List<User> read();
    void update(User user, Long id);
    void delete(Long id);
    User getByID(Long id);
    List<Long> getIdList();
}
