package me.filipebezerra.cms.domain.service;

import me.filipebezerra.cms.domain.models.Category;
import me.filipebezerra.cms.domain.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category update(Category category) {
        return categoryRepository.save(category);
    }

    public Category create(Category category) {
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
