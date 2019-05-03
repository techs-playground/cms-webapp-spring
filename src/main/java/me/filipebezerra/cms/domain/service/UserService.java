package me.filipebezerra.cms.domain.service;

import me.filipebezerra.cms.domain.exceptions.UserNotFoundException;
import me.filipebezerra.cms.domain.models.User;
import me.filipebezerra.cms.domain.repository.UserRepository;
import me.filipebezerra.cms.domain.vo.UserRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findOne(String id) {
        final Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException(id);
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public User create(UserRequest userRequest) {
        final User user = new User();
        user.setIdentity(userRequest.getIdentity());
        user.setName(userRequest.getName());
        user.setRole(userRequest.getRole());
        return userRepository.save(user);
    }

    @Transactional
    public User update(String id, UserRequest userRequest) {
        final Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            final User userUpdated = user.get();
            userUpdated.setIdentity(userRequest.getIdentity());
            userUpdated.setName(userRequest.getName());
            userUpdated.setRole(userRequest.getRole());
            return userRepository.save(userUpdated);
        } else {
            throw new UserNotFoundException(id);
        }
    }

    @Transactional
    public void delete(String id) {
        userRepository.deleteById(id);
    }

}
