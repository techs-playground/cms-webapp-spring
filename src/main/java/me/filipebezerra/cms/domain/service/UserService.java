package me.filipebezerra.cms.domain.service;

import me.filipebezerra.cms.domain.models.User;
import me.filipebezerra.cms.domain.repository.UserRepository;
import me.filipebezerra.cms.domain.vo.UserRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<User> findOne(String id) {
        return userRepository.findById(id);
    }

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    public Mono<User> create(UserRequest userRequest) {
        final User user = new User();
        user.setIdentity(userRequest.getIdentity());
        user.setName(userRequest.getName());
        user.setRole(userRequest.getRole());
        return userRepository.save(user);
    }

    public Mono<User> update(String id, UserRequest userRequest) {
        return userRepository.findById(id).flatMap(user -> {
            user.setIdentity(userRequest.getIdentity());
            user.setName(userRequest.getName());
            user.setRole(userRequest.getRole());
            return userRepository.save(user);
        });
    }

    public void delete(String id) {
        userRepository.deleteById(id);
    }

}
