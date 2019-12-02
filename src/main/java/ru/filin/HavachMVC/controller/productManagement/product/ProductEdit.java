package ru.filin.HavachMVC.controller.productManagement.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.filin.HavachMVC.service.productManagement.category.CategoryService;
import ru.filin.HavachMVC.service.productManagement.product.ProductService;

@Controller
@RequestMapping("/product/management")
public class ProductEdit {

    private CategoryService categoryService;
    private ProductService productService;

    @Autowired
    public ProductEdit(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping
    public String getProductAddPage(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        return "productPages/addproduct";
    }
}
