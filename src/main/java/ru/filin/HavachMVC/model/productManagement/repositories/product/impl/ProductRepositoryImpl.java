package ru.filin.HavachMVC.model.productManagement.repositories.product.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.filin.HavachMVC.model.productManagement.entities.Product;
import ru.filin.HavachMVC.model.productManagement.repositories.product.ProductRepository;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Product> getAll() {
        String query = "SELECT * FROM products";
        return jdbcTemplate.query(query, ROW_MAPPER);
    }

    @Override
    public Product getById(long id) {
        String query = "SELECT * FROM products WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, ROW_MAPPER);
    }

    @Override
    public void delete(long id) {
        String query = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(query, id);

    }

    @Override
    public void update(Product obj) {
        String query = "UPDATE products SET (name, price, description, details_id, stock, active,  photo) VALUES (?, ?, ?, ?, ?, ?, ?) WHERE id = ?";
        jdbcTemplate.update(
                query,
                obj.getName(),
                obj.getPrice(),
                obj.getDescription(),
                obj.getDetailsId(),
                obj.getStock(),
                obj.isActive(),
                obj.getPhoto(),
                obj.getId(),
                obj.getCategoryId()
        );
    }

    @Override
    public Long save(Product obj) {

        String getProductId = "SELECT nextval(pg_get_serial_sequence('products', 'id'))";

        long productId = jdbcTemplate.query(getProductId, rs -> {
            rs.next();
            return rs.getLong("nextval");
        });

        String productSave = "INSERT INTO " +
                "products (id, description, price, details_id, stock, photo, name, category_id, active) " +
                "VALUES (?,?,?,?,?,?,?,?,?);";

        jdbcTemplate.update(
                productSave,
                productId,
                obj.getDescription(),
                obj.getPrice(),
                obj.getDetailsId(),
                obj.getStock(),
                obj.getPhoto(),
                obj.getName(),
                obj.getCategoryId(),
                obj.isActive()
        );


        return productId;
//        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
//        jdbcInsert.withTableName("product").usingGeneratedKeyColumns(
//                "Primary_key");
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("name", obj.getName());
//        parameters.put("price", obj.getPrice());
//        parameters.put("description", obj.getDescription());
//        parameters.put("details_id", obj.getDetailsId());
//        parameters.put("stock", obj.getStock());
//        parameters.put("active", obj.isActive());
//        parameters.put("photo", obj.getPhoto());
//        parameters.put("category_id", obj.getCategoryId());
//        return (Long) jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(
//                parameters));
    }

    @Override
    public List<Product> findByCategoryId(long category_id) {
        String findProductByCategoryId = "SELECT * FROM products WHERE category_id = ?";
        return jdbcTemplate.query(findProductByCategoryId, new Object[]{category_id}, ROW_MAPPER);
    }
}
