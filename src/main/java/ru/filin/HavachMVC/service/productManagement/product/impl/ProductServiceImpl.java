package ru.filin.HavachMVC.service.productManagement.product.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.filin.HavachMVC.model.productManagement.entities.Product;
import ru.filin.HavachMVC.model.productManagement.repositories.product.ProductRepository;
import ru.filin.HavachMVC.service.productManagement.product.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.getAll();
    }

    @Override
    public Product getById(long id) {
        return productRepository.getById(id);
    }

    @Override
    public void delete(long id) {
        productRepository.delete(id);
    }

    @Override
    public void update(Product obj) {
        productRepository.update(obj);
    }

    @Override
    public Long save(Product obj) {
        return productRepository.save(obj);
    }
}
