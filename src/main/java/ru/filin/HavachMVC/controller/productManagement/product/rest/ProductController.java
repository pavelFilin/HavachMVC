package ru.filin.HavachMVC.controller.productManagement.product.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.filin.HavachMVC.model.productManagement.entities.Product;
import ru.filin.HavachMVC.service.productManagement.product.ProductService;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    @GetMapping("{category_id}")
    public  String getProductsByCategory(@PathVariable long category_id, Model model) {
        List<Product> products = productService.findByCategoryId(category_id);
        model.addAttribute("products", products);
        return "shopPages/productlist";
    }
}
