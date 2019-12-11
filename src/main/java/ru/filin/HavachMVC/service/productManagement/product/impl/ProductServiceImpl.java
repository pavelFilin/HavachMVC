package ru.filin.HavachMVC.service.productManagement.product.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.filin.HavachMVC.model.productManagement.entities.Category;
import ru.filin.HavachMVC.model.productManagement.entities.Product;
import ru.filin.HavachMVC.model.productManagement.repositories.category.CategoryRepository;
import ru.filin.HavachMVC.model.productManagement.repositories.product.ProductRepository;
import ru.filin.HavachMVC.service.productManagement.category.impl.CategoryServiceImpl;
import ru.filin.HavachMVC.service.productManagement.product.ProductService;
import ru.filin.HavachMVC.utils.FileHelper;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Value("${upload.path}")
    private String uploadPath;

    private ProductRepository productRepository;

    private CategoryRepository categoryRepository;

    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = productRepository.getAll();
        logger.info("get all products " + products.stream().collect(Collectors.toMap(Product::getId, Function.identity())));
        return products;
    }

    @Override
    public Product getById(long id) {
        logger.info("get product by productId=" + id);
        return productRepository.getById(id);
    }

    @Override
    public void delete(long id) {
        logger.info("delete product by productId=" + id);
        productRepository.delete(id);
    }

    @Override
    public void update(Product obj) {
        logger.info("update product productId=" + obj.getId());
        productRepository.update(obj);
    }

    @Override
    public Long save(Product obj) {
        logger.info("save product name=" + obj.getName());
        return productRepository.save(obj);
    }

    @Override
    public List<Product> findByCategoryId(long category_id) {
        List<Product> products = productRepository.findByCategoryId(category_id);
        logger.info("get product by CategoryId=" + category_id + " " + products.stream().collect(Collectors.toMap(Product::getId, Function.identity())));
        return products;
    }

    @Override
    public long add(Product product, MultipartFile file, String category) throws IOException {
        String path = FileHelper.loadFile(file, uploadPath);
        Category categoryFromDB = categoryRepository.getByTitle(category);
        product.setCategoryId(categoryFromDB.getId());
        product.setPhoto(path);
        logger.info("add new product product name=" + product.getName() + "product stock=" + product.getStock() +
                "photo path=" + path);
        return productRepository.save(product);
    }

    @Override
    public List<Product> getByNameAndActive(String name, String active) {
        logger.info("get by product name=" + name + " active=" + active);
        return productRepository.getByNameAndActive(name, active);
    }

    @Override
    public void edit(Product product, MultipartFile file, String category) throws IOException {
        if (!file.isEmpty()) {
            String path = FileHelper.loadFile(file, uploadPath);
            product.setPhoto(path);
        }
        Category categoryFromDB = categoryRepository.getByTitle(category);
        product.setCategoryId(categoryFromDB.getId());
        logger.info("update product " + product.getId());
        productRepository.update(product);
    }
}
