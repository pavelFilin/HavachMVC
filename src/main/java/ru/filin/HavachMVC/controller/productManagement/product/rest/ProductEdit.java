package ru.filin.HavachMVC.controller.productManagement.product.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.filin.HavachMVC.model.productManagement.entities.Category;
import ru.filin.HavachMVC.service.productManagement.category.CategoryService;
import ru.filin.HavachMVC.service.productManagement.product.ProductService;

@Controller
@RequestMapping("/product/management")
public class ProductEdit {

    private CategoryService categoryService;
    private ProductService productService;

    public ProductEdit(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping
    public String getProductAddPage(Model model) {
        model.addAttribute(categoryService.getAll());
        return "productPages/addproduct";
    }
}
