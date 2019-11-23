package ru.filin.HavachMVC.model.productManagement.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.filin.HavachMVC.model.productManagement.entities.Product;
import ru.filin.HavachMVC.model.productManagement.repositories.ProductRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Product> getAll() {

        String query = "SELECT * FROM products";

        return jdbcTemplate.query(query, new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                Product product = new Product();
                while (rs.next()) {
                    product.setId(rs.getLong("id"));
                    product.setPrice(rs.getInt("price"));
                    product.setDescription(rs.getString("description"));
                }
                return product;
            }
        });
    }

    @Override
    public Product getById(long id) {
        String query = "SELECT * FROM products WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, BeanPropertyRowMapper.newInstance(Product.class));
    }

    @Override
    public void delete(long id) {
        String query = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(query, id);

    }

    @Override
    public void update(Product obj) {
        String query = "UPDATE products SET (name, price, description, details_id, warehouse_id, photo) VALUES (?, ?, ?, ?, ?, ?) WHERE id = ?";
        jdbcTemplate.update(query, new Object[]{obj.getName(), obj.getPrice(), obj.getDescription(), obj.getDetails_id(), obj.getWarehouse_id(), obj.getPhoto(), obj.getId()});
    }

    @Override
    public Long save(Product obj) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("product").usingGeneratedKeyColumns(
                "Primary_key");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", obj.getName());
        parameters.put("price", obj.getPrice());
        parameters.put("description", obj.getDescription());
        parameters.put("details_id", obj.getDetails_id());
        parameters.put("warehouse_id", obj.getWarehouse_id());
        parameters.put("photo", obj.getPrice());
        return (Long) jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(
                parameters));
    }
}
