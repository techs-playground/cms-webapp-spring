package me.filipebezerra.cms.domain.repository;

import me.filipebezerra.cms.domain.models.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface CategoryRepository extends ReactiveMongoRepository<Category, String> {

    Flux<Category> findByName(String name);

    Flux<Category> findByNameIgnoreCaseStartingWith(String name);

}