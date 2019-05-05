package me.filipebezerra.cms.domain.repository;

import me.filipebezerra.cms.domain.models.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepository extends ReactiveMongoRepository<User, String> { }