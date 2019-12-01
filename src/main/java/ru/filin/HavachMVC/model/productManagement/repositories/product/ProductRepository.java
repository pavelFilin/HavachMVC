package ru.filin.HavachMVC.model.productManagement.repositories.product;

import org.springframework.jdbc.core.RowMapper;
import ru.filin.HavachMVC.model.BaseRepository;
import ru.filin.HavachMVC.model.productManagement.entities.Category;
import ru.filin.HavachMVC.model.productManagement.entities.Product;

import java.sql.ResultSet;

public interface ProductRepository extends BaseRepository<Product> {
    RowMapper<Product> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new Product(resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getInt("price"),
                resultSet.getLong("details_id"),
                resultSet.getInt("stock"),
                resultSet.getBoolean("active"),
                resultSet.getString("photo")
        );
    };

}
