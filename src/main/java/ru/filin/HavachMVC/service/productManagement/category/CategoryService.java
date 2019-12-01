package ru.filin.HavachMVC.service.productManagement.category;

import ru.filin.HavachMVC.model.productManagement.entities.Category;
import ru.filin.HavachMVC.service.BaseService;

public interface CategoryService extends BaseService<Category> {
    Category findByTitle(String title);

    void addCategory(String title, String id);
}
