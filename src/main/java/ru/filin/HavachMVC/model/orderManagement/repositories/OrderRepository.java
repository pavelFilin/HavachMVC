package ru.filin.HavachMVC.model.orderManagement.repositories;

import org.springframework.jdbc.core.RowMapper;
import ru.filin.HavachMVC.constants.OrderStatus;
import ru.filin.HavachMVC.constants.PaymentStatus;
import ru.filin.HavachMVC.constants.PaymentType;
import ru.filin.HavachMVC.model.BaseRepository;
import ru.filin.HavachMVC.model.orderManagement.entities.CartItem;
import ru.filin.HavachMVC.model.orderManagement.entities.Order;
import ru.filin.HavachMVC.model.orderManagement.entities.OrderItemFull;
import ru.filin.HavachMVC.model.productManagement.entities.Product;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderRepository extends BaseRepository<Order> {
    RowMapper<Order> OderMapper = (ResultSet resultSet, int rowNum) -> {
        return new Order(
                resultSet.getLong("id"),
                resultSet.getLong("user_id"),
                new Date(resultSet.getTimestamp("time_created").getTime()),
                PaymentType.valueOf(resultSet.getString("payment_type")),
                resultSet.getInt("quantity"),
                resultSet.getString("phone"),
                resultSet.getString("address"),
                resultSet.getInt("finalPrice"),
                OrderStatus.valueOf(resultSet.getString("orderStatus")),
                PaymentStatus.valueOf(resultSet.getString("paymentStatus"))
        );
    };

    RowMapper<OrderItemFull> OderItemMapper = (ResultSet resultSet, int rowNum) -> {
        return new OrderItemFull(
                resultSet.getLong("id"),
                resultSet.getLong("productId"),
                resultSet.getLong("orderId"),
                resultSet.getInt("price"),
                resultSet.getInt("quantity"),
                resultSet.getInt("totalPrice")
        );
    };


    long saveOrderAndOrderItems(Order order, Map<CartItem, Product> cartMap);

    Order findOrderByIdAndUserID(long orderId, long userId);

    List<OrderItemFull> findOrderItemsByOrderId(long orderId);
}
