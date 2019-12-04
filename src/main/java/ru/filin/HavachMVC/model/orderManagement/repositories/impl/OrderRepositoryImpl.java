package ru.filin.HavachMVC.model.orderManagement.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.filin.HavachMVC.model.orderManagement.entities.Order;
import ru.filin.HavachMVC.model.orderManagement.repositories.OrderRepository;

import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Order> getAll() {
        String query = "SELECT * FROM order_table";
        return jdbcTemplate.query(query, ROW_MAPPER);
    }

    @Override
    public Order getById(long id) {
        String query = "SELECT * FROM order_table WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, ROW_MAPPER);
    }

    @Override
    public void delete(long id) {
        String query = "DELETE FROM order_table WHERE id = ?";
        jdbcTemplate.update(query, new Object[]{id});
    }

    @Override
    public void update(Order obj) {
        String query = "UPDATE order_table SET user_id = ?, time_created = ?, payment_type = ?, address = ?, finalprice = ?, orderstatus = ? WHERE id = ?";
        jdbcTemplate.update(query,
                new Object[]{
                        obj.getUserId(),
                        obj.getTimeCreated(),
                        obj.getPaymentType(),
                        obj.getAddress(),
                        obj.getFinalPrice(),
                        obj.getOrderStatus(),
                        obj.getId()
                });
    }

    @Override
    public Long save(Order obj) {

        String getOrderId = "SELECT nextval(pg_get_serial_sequence('cart', 'id'))";

        long orderId = jdbcTemplate.query(getOrderId, rs -> {
            rs.next();
            return rs.getLong("nextval");
        });

        String query = "INSERT INTO order_table (id, user_id, time_created, payment_type, address, finalprice, orderstatus) VALUES (?,?,?,?,?,?,?)";
        jdbcTemplate.update(query,
                new Object[]{
                        orderId,
                        obj.getUserId(),
                        obj.getTimeCreated(),
                        obj.getPaymentType(),
                        obj.getAddress(),
                        obj.getFinalPrice(),
                        obj.getOrderStatus(),
                });
        return orderId;
    }
}
