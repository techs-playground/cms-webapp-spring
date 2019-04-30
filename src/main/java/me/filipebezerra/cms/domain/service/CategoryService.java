package me.filipebezerra.cms.domain.service;

import me.filipebezerra.cms.domain.models.Category;
import me.filipebezerra.cms.domain.repository.CategoryRepository;
import me.filipebezerra.cms.domain.vo.CategoryRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category update(String id, CategoryRequest categoryRequest) {
        final Category category = categoryRepository.findOne(id);
        category.setName(categoryRequest.getName());
        return categoryRepository.save(category);
    }

    public Category create(CategoryRequest categoryRequest) {
        final Category category = new Category();
        category.setId(UUID.randomUUID().toString());
        category.setName(categoryRequest.getName());
        return categoryRepository.save(category);
    }

    public void delete(String id) {
        Category category = categoryRepository.findOne(id);
        categoryRepository.delete(category);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findOne(String id) {
        return categoryRepository.findOne(id);
    }

}
