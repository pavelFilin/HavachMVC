package ru.filin.HavachMVC.model.orderManagement.repositories;

import org.springframework.jdbc.core.RowMapper;
import ru.filin.HavachMVC.model.BaseRepository;
import ru.filin.HavachMVC.model.orderManagement.entities.Cart;

import java.sql.ResultSet;

public interface CartRepository extends BaseRepository<Cart> {
    RowMapper<Cart> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new Cart(
                resultSet.getLong("id"),
                resultSet.getLong("user_id"),
                resultSet.getInt("count_item"),
                resultSet.getInt("total_price")
        );
    };

    Cart getByUserId(long userId);

    void addNewItem(Cart cart, long productId, int quantity);

}
