package me.filipebezerra.cms.domain.repository;

import me.filipebezerra.cms.domain.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, String> {

    List<Category> findByName(String name);

    List<Category> findByNameIgnoreCaseStartingWith(String name);

}