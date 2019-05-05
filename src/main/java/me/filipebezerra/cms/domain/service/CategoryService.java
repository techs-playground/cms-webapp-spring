package me.filipebezerra.cms.domain.service;

import me.filipebezerra.cms.domain.models.Category;
import me.filipebezerra.cms.domain.repository.CategoryRepository;
import me.filipebezerra.cms.domain.vo.CategoryRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Mono<Category> findOne(String id) {
        return categoryRepository.findById(id);
    }

    public Flux<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Flux<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }

    public Flux<Category> findByNameStartingWith(String name) {
        return categoryRepository.findByNameIgnoreCaseStartingWith(name);
    }

    public Mono<Category> create(CategoryRequest categoryRequest) {
        final Category category = new Category();
        category.setName(categoryRequest.getName());
        return categoryRepository.save(category);
    }

    public Mono<Category> update(String id, CategoryRequest categoryRequest) {
        return categoryRepository.findById(id).flatMap(category -> {
            category.setName(categoryRequest.getName());
            return categoryRepository.save(category);
        });
    }

    public void delete(String id) {
        categoryRepository.deleteById(id);
    }

}
