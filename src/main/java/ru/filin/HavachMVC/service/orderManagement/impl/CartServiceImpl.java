package ru.filin.HavachMVC.service.orderManagement.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.filin.HavachMVC.model.orderManagement.entities.Cart;
import ru.filin.HavachMVC.model.orderManagement.repositories.CartRepository;
import ru.filin.HavachMVC.service.orderManagement.CartService;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public List<Cart> getAll() {
        return cartRepository.getAll();
    }

    @Override
    public Cart getById(long id) {
        return cartRepository.getById(id);
    }

    @Override
    public void delete(long id) {
        cartRepository.delete(id);
    }

    @Override
    public void update(Cart obj) {
        cartRepository.update(obj);
    }

    @Override
    public Long save(Cart obj) {
        return cartRepository.save(obj);
    }

    @Override
    public void addNewCartItem(long userId, long productId, int quantity) {
        Cart cart = cartRepository.getByUserId(userId);
        if (cart == null) {
            cart  = new Cart(userId, 0, 0);
            long cartId = cartRepository.save(cart);
            cart.setId(cartId);
        }

        cartRepository.addNewItem(cart, productId, quantity);
    }

    @Override
    public Cart getByUserId(long userId) {
        return cartRepository.getByUserId(userId);
    }

}
