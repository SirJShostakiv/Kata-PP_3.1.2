package app.service;

import app.model.User;
import app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Transactional
    @Override
    public void create(User user) {
        userRepository.save(user);
    }

    @Transactional
    @Override
    public List<User> read() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void update(String name, String lastName, int age, Long id) {
        User user = userRepository.getReferenceById(id);
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public User getByID(Long id) { return userRepository.getReferenceById(id);}
}
