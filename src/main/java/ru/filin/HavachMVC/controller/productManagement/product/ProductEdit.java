package ru.filin.HavachMVC.controller.productManagement.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.filin.HavachMVC.model.productManagement.entities.Product;
import ru.filin.HavachMVC.service.productManagement.category.CategoryService;
import ru.filin.HavachMVC.service.productManagement.product.ProductService;

import java.io.IOException;

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

    @PostMapping
    public String addProduct(Product product, @RequestParam("file") MultipartFile file, @RequestParam String category) {
        long productId = 0;
        try {
            productId = productService.add(product, file, category);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/product/" + productId;
    }
}
