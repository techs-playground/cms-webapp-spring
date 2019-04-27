package me.filipebezerra.cms.domain.service;

import me.filipebezerra.cms.domain.models.User;
import me.filipebezerra.cms.domain.repository.UserRepository;
import me.filipebezerra.cms.domain.vo.UserRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User update(String id, UserRequest userRequest) {
        final User user = userRepository.findOne(id);
        user.setIdentity(userRequest.getIdentity());
        user.setName(user.getName());
        user.setRole(userRequest.getRole());
        return userRepository.save(user);
    }

    public User create(UserRequest userRequest) {
        final User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setIdentity(userRequest.getIdentity());
        user.setName(user.getName());
        user.setRole(userRequest.getRole());
        return userRepository.save(user);
    }

    public void delete(String id) {
        User user = userRepository.findOne(id);
        userRepository.delete(user);
    }

    public User findOne(String id) {
        return userRepository.findOne(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

}
