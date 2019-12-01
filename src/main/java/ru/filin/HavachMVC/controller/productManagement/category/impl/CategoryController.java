package ru.filin.HavachMVC.controller.productManagement.category.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.filin.HavachMVC.model.productManagement.entities.Category;
import ru.filin.HavachMVC.service.productManagement.category.CategoryService;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getCategories(Model model) {
        List<Category> categories = categoryService.getAll();

        model.addAttribute("categories", categories);
        return "userPages/categorymanagement";
    }

    @PostMapping
    public String addNewCategory(@RequestParam String title, @RequestParam(required = false) String parentId) {
        categoryService.addCategory(title, parentId);
        return "redirect:/category";
    }


}
