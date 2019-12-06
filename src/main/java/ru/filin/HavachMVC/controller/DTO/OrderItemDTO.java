package ru.filin.HavachMVC.controller.DTO;

import lombok.Data;
import ru.filin.HavachMVC.model.productManagement.entities.Product;

@Data
public class OrderItemDTO {
    private Product product;

    private long orderId;

    private int price;

    private int quantity;

    private int totalPrice;
}
