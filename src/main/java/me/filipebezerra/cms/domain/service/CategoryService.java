package me.filipebezerra.cms.domain.service;

import me.filipebezerra.cms.domain.exceptions.CategoryNotFoundException;
import me.filipebezerra.cms.domain.models.Category;
import me.filipebezerra.cms.domain.repository.CategoryRepository;
import me.filipebezerra.cms.domain.vo.CategoryRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category findOne(String id) {
        final Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            return category.get();
        } else {
            throw new CategoryNotFoundException(id);
        }
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Transactional
    public Category create(CategoryRequest categoryRequest) {
        final Category category = new Category();
        category.setName(categoryRequest.getName());
        return categoryRepository.save(category);
    }

    @Transactional
    public Category update(String id, CategoryRequest categoryRequest) {
        final Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            final Category categoryUpdated = category.get();
            categoryUpdated.setName(categoryRequest.getName());
            return categoryRepository.save(categoryUpdated);
        } else {
            throw new CategoryNotFoundException(id);
        }
    }

    @Transactional
    public void delete(String id) {
        final Optional<Category> category = categoryRepository.findById(id);
        category.ifPresent(categoryRepository::delete);
    }

    public List<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }

    public List<Category> findByNameStartingWith(String name) {
        return categoryRepository.findByNameIgnoreCaseStartingWith(name);
    }

}
