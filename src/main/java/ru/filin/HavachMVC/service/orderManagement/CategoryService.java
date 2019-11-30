package ru.filin.HavachMVC.service.orderManagement;

import ru.filin.HavachMVC.model.orderManagement.entities.Category;
import ru.filin.HavachMVC.service.BaseService;

public interface CategoryService extends BaseService<Category> {
    Category findByTitle(String title);
}
