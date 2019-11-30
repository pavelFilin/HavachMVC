package ru.filin.HavachMVC.service.orderManagement.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.filin.HavachMVC.model.orderManagement.entities.Category;
import ru.filin.HavachMVC.model.orderManagement.repositories.CategoryRepository;
import ru.filin.HavachMVC.service.orderManagement.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findByTitle(String title) {
        return categoryRepository.getByTitle(title);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.getAll();
    }

    @Override
    public Category getById(long id) {
        return categoryRepository.getById(id);
    }

    @Override
    public void delete(long id) {
        categoryRepository.delete(id);
    }

    @Override
    public void update(Category obj) {
        categoryRepository.update(obj);
    }

    @Override
    public Long save(Category obj) {
        return categoryRepository.save(obj);
    }
}
