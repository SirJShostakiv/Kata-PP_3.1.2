package app.service;

import app.dao.UserDAOImpl;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAOImpl userDAOImpl;
    @Autowired
    public UserServiceImpl(UserDAOImpl userDAOImpl) {
        this.userDAOImpl = userDAOImpl;
    }
    @Transactional
    @Override
    public void create(User user) {
        userDAOImpl.create(user);
    }

    @Transactional
    @Override
    public List<User> read() {
        return userDAOImpl.read();
    }

    @Transactional
    @Override
    public void update(User user, Long id) {
        userDAOImpl.update(user, id);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        userDAOImpl.delete(id);
    }

    @Transactional
    @Override
    public User getByID(Long id) { return userDAOImpl.getByID(id);}
    @Override
    public List<Long> getIdList() {
        return userDAOImpl.getIdList();
    }
}
