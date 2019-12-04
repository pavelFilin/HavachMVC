package ru.filin.HavachMVC.controller.productManagement.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.filin.HavachMVC.controller.DTO.ProductDTO;
import ru.filin.HavachMVC.model.productManagement.entities.Category;
import ru.filin.HavachMVC.model.productManagement.entities.Product;
import ru.filin.HavachMVC.service.productManagement.category.CategoryService;
import ru.filin.HavachMVC.service.productManagement.product.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    private CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }



    @GetMapping("{productId}")
    public String getProduct(@PathVariable long productId, Model model) {
        Product product = productService.getById(productId);
        model.addAttribute("product", product);
        return "productPages/productview";
    }

    @GetMapping("adminproductlist")
    public String getAdminProductList(Model model) {
        List<Product> products = productService.getAll();
        List<Category> categories = categoryService.getAll();

        List<ProductDTO> productDTOS = products.stream().map(product -> fillCategory(product, categories)).collect(Collectors.toList());

        model.addAttribute("products", productDTOS);
        return "productPages/adminproductlist";
    }

    private ProductDTO fillCategory(Product product, List<Category> categories) {
        ProductDTO productDTO = new ProductDTO(product);
        Category categoryDTO = categories.stream().filter(category -> category.getId() == product.getCategoryId()).findAny().orElse(null);
        productDTO.setCategory(categoryDTO);
        return productDTO;
    }
}
