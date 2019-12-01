package ru.filin.HavachMVC.controller.productManagement.category.impl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.filin.HavachMVC.model.productManagement.entities.Category;
import ru.filin.HavachMVC.service.productManagement.category.CategoryService;

import java.util.List;

@Controller
@RequestMapping("catalog")
public class CatalogController {

    private CategoryService categoryService;

    public CatalogController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String catalog(Model model) {
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categories", categories);
        return "shopPages/catalog";
    }
}
