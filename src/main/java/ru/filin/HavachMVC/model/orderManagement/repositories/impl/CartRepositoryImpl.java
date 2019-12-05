package ru.filin.HavachMVC.model.orderManagement.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.filin.HavachMVC.model.orderManagement.entities.Cart;
import ru.filin.HavachMVC.model.orderManagement.repositories.CartRepository;

import java.util.List;

@Repository
public class CartRepositoryImpl implements CartRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CartRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Cart> getAll() {
        String getAllCart = "SELECT * FROM cart";
        return jdbcTemplate.query(getAllCart, ROW_MAPPER);
    }

    @Override
    public Cart getById(long id) {
        String getCartById = "SELECT * FROM cart WHERE id = ?";
        return jdbcTemplate.queryForObject(getCartById, new Object[]{id}, ROW_MAPPER);
    }

    @Override
    public void delete(long id) {
        String deleteCart = "DELETE FROM cart WHERE id = ?";
        jdbcTemplate.update(deleteCart, new Object[]{id});
    }

    @Override
    public void update(Cart obj) {
        String getAllCart = "UPDATE cart c SET user_id = ?  WHERE id = ?";
        jdbcTemplate.update(getAllCart,
                new Object[]{
                        obj.getUserId(),
                        obj.getId()
                }
        );
    }

    @Override
    public Long save(Cart obj) {
        String getCartId = "SELECT nextval(pg_get_serial_sequence('cart', 'id'))";

        long cartId = jdbcTemplate.query(getCartId, rs -> {
            rs.next();
            return rs.getLong("nextval");
        });

        String saveCart = "INSERT INTO cart (id, user_id, total_price, count_item) VALUES (?,?,?,?)";
        jdbcTemplate.update(saveCart,
                new Object[]{
                        cartId,
                        obj.getUserId(),
                        obj.getTotalPrice(),
                        obj.getCountItem()
                }
        );

        return cartId;
    }

    @Override
    public Cart getByUserId(long userId) {
        String getCartById = "SELECT * FROM cart WHERE user_id = ?";
        return jdbcTemplate.queryForObject(getCartById, new Object[]{userId}, ROW_MAPPER);
    }

    @Override
    public void addNewItem(Cart cart, long productId, int quantity) {
        addNewItemTransaction(cart, productId, quantity);
    }

    @Transactional
    protected void addNewItemTransaction(Cart cart, long product_id, int quantity) {

        String addCartItem = "INSERT INTO cart_item (cart_id, product_id, quantity) values (?,?,?)";
        jdbcTemplate.update(addCartItem);

        update(cart);
    }

    @Transactional
    protected void updateNewItemTransaction(Cart cart, long product_id, int quantity) {

        String addCartItem = "UPDATE cart_item SET cart_id = ?, product_id = ?, quantity = ? WHERE id = ?";
        jdbcTemplate.update(addCartItem);

        update(cart);
    }
}
