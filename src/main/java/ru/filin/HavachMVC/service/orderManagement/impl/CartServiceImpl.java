package ru.filin.HavachMVC.service.orderManagement.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.filin.HavachMVC.model.orderManagement.entities.CartItem;
import ru.filin.HavachMVC.model.orderManagement.repositories.impl.CartRepositoryImpl;

import java.util.List;

@Service
public class CartServiceImpl {

    private CartRepositoryImpl cartRepository;

    @Autowired
    public CartServiceImpl(CartRepositoryImpl cartRepository) {
        this.cartRepository = cartRepository;
    }

    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    public List<CartItem> findCartsByUser(long userId) {
        logger.info("find cart by userId=" + userId);
        return cartRepository.findCartsByUser(userId);
    }

    public CartItem findCartByUserAndProduct(long userId, long productId) {
        logger.info("find cart by userId=" + userId + " and product=" + productId);
        return cartRepository.findCartByUserAndProduct(userId, productId);
    }

    public void saveCartItem(CartItem cart) {
        logger.info("save cartItem userId=" + cart.getUserId());
        cartRepository.saveCartItem(cart);
    }

    public void saveCartItem(long userId, long productId, int quantity) {
        logger.info("save cartItem userId=" + userId + " productId=" + productId + " quantity=" + quantity);
        CartItem cartItem = new CartItem(userId, productId, quantity);
        saveCartItem(cartItem);
    }

    public void purgeCartByUser(long userId) {
        logger.info("purge cart userId=" + userId);
        cartRepository.purgeCartByUser(userId);
    }

    public void changeQuantity(long userId, long productId, int quantity) {
        logger.info("change quantity userId=" + userId + " productId=" + productId + " quantity=" + quantity);
        cartRepository.changeQuantity(userId, productId, quantity);
    }

    public void deleteCartItem(long cartId) {
        logger.info("delete cartItem cartId=" + cartId);
        cartRepository.deleteCartItem(cartId);
    }
}
