package ru.filin.HavachMVC.service.orderManagement;

import ru.filin.HavachMVC.model.BaseRepository;
import ru.filin.HavachMVC.model.orderManagement.entities.Cart;

public interface CartService extends BaseRepository<Cart> {
    void addNewCartItem(long userID, long productId, int quantity);

    Cart getByUserId(long userId);
}
