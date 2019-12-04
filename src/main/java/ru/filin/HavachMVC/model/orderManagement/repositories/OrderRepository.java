package ru.filin.HavachMVC.model.orderManagement.repositories;

import org.springframework.jdbc.core.RowMapper;
import ru.filin.HavachMVC.constants.OrderStatus;
import ru.filin.HavachMVC.constants.PaymentType;
import ru.filin.HavachMVC.model.BaseRepository;
import ru.filin.HavachMVC.model.orderManagement.entities.Order;

import java.sql.ResultSet;
import java.util.Date;

public interface OrderRepository extends BaseRepository<Order> {
    RowMapper<Order> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new Order(
                resultSet.getLong("id"),
                resultSet.getLong("user_id"),
                new Date(resultSet.getTimestamp("time_created").getTime()),
                PaymentType.valueOf(resultSet.getString("payment_type")),
                resultSet.getLong("paymentId"),
                resultSet.getString("address"),
                resultSet.getInt("finalPrice"),
                OrderStatus.valueOf(resultSet.getString("orderStatus"))
        );
    };


}
