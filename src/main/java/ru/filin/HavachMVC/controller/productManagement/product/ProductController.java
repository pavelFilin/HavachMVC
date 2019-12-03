package ru.filin.HavachMVC.controller.productManagement.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.filin.HavachMVC.model.productManagement.entities.Product;
import ru.filin.HavachMVC.service.productManagement.category.CategoryService;
import ru.filin.HavachMVC.service.productManagement.product.ProductService;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    private CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("productlist/{categoryId}")
    public  String getProductsByCategory(@PathVariable long categoryId, Model model) {
        List<Product> products = productService.findByCategoryId(categoryId);
        model.addAttribute("products", products);
        return "shopPages/productlist";
    }

    @GetMapping("{productId}")
    public String getProduct(@PathVariable long productId, Model model) {
        Product product = productService.getById(productId);
        model.addAttribute("product", product);
        return "productPages/productview";
    }
}
