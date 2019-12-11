package ru.filin.HavachMVC.service.productManagement.category.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.filin.HavachMVC.model.productManagement.entities.Category;
import ru.filin.HavachMVC.model.productManagement.repositories.category.CategoryRepository;
import ru.filin.HavachMVC.service.productManagement.category.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findByTitle(String title) {
        Category category = categoryRepository.getByTitle(title);
        logger.info("get category by title=" + title);
        return category;
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = categoryRepository.getAll();
        logger.info("get all categories" + categories.stream().map(Category::getTitle).collect(Collectors.toList()).toString());
        return categories;
    }

    @Override
    public Category getById(long id) {
        Category category = categoryRepository.getById(id);
        logger.info("get category by id=" + id);
        return category;
    }

    @Override
    public void delete(long id) {
        logger.info("delete category by id=" + id);
        categoryRepository.delete(id);
    }

    @Override
    public void update(Category obj) {
        logger.info("update category by id="+ obj.getId() + "title " + obj.getTitle());
        categoryRepository.update(obj);
    }

    @Override
    public Long save(Category obj) {
        logger.info("save category title=" + obj.getTitle());
        return categoryRepository.save(obj);
    }

    @Override
    public void addCategory(String title, String parentIdStr) {
        long parentId = -1;
        if (!StringUtils.isEmpty(parentIdStr)) {
            parentId = parseId(parentIdStr);
        }
        Category category = new Category(title, parentId);
        categoryRepository.save(category);
    }

    @Override
    public List<Long> getChildIds(long id) {
        return categoryRepository.findByParentId(id);
    }

    private long parseId(String id) {
        StringBuilder idString = new StringBuilder();
        for (int i = 0; i < id.length(); i++) {
            if (Character.isDigit(id.charAt(i))) {
                idString.append(id.charAt(i));
            } else {
                break;
            }
        }
        return Long.parseLong(idString.toString());
    }
}
