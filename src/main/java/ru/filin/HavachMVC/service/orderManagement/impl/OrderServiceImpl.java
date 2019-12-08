package ru.filin.HavachMVC.service.orderManagement.impl;

import org.springframework.stereotype.Service;
import ru.filin.HavachMVC.constants.OrderStatus;
import ru.filin.HavachMVC.constants.PaymentStatus;
import ru.filin.HavachMVC.constants.PaymentType;
import ru.filin.HavachMVC.controller.DTO.OrderDTO;
import ru.filin.HavachMVC.controller.DTO.OrderItemDTO;
import ru.filin.HavachMVC.model.orderManagement.entities.CartItem;
import ru.filin.HavachMVC.model.orderManagement.entities.Order;
import ru.filin.HavachMVC.model.orderManagement.entities.OrderItemFull;
import ru.filin.HavachMVC.model.orderManagement.repositories.OrderRepository;
import ru.filin.HavachMVC.model.productManagement.entities.Product;
import ru.filin.HavachMVC.service.productManagement.product.ProductService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl {

    private CartServiceImpl cartService;

    private OrderRepository orderRepository;

    private ProductService productService;

    public OrderServiceImpl(CartServiceImpl cartService, OrderRepository orderRepository, ProductService productService) {
        this.cartService = cartService;
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    public long makeOrder(long userId,
                          String phone,
                          String address,
                          String paymentType
    ) {
        List<CartItem> cartItems = cartService.findCartsByUser(userId);
        Map<CartItem, Product> cartMap = new HashMap<>();
        for (CartItem cartItem : cartItems) {
            cartMap.put(cartItem, productService.getById(cartItem.getProductId()));
        }

        int count = 0;
        int finalPrice = 0;

        for (Map.Entry<CartItem, Product> entry : cartMap.entrySet()) {
            count += entry.getKey().getQuantity();
            finalPrice += entry.getKey().getQuantity() * entry.getValue().getPrice();
        }

        Order order = new Order(userId,
                new Date(),
                PaymentType.valueOf(paymentType),
                count,
                phone,
                address,
                finalPrice,
                OrderStatus.PROCESSING,
                PaymentStatus.PENDING
        );
        return saveOrderAndOrderItems(order, cartMap);
    }

    private long saveOrderAndOrderItems(Order order, Map<CartItem, Product> cartMap) {
        return orderRepository.saveOrderAndOrderItems(order, cartMap);
    }


    public OrderDTO getOrderByUserIdAndOrderId(long userId, long orderId) {
        Order order = orderRepository.findOrderByIdAndUserID(orderId, userId);
        List<OrderItemFull> orderItems = orderRepository.findOrderItemsByOrderId(userId);
        List<OrderItemDTO> orderItemDTOS = orderItems.stream()
                .map(oi -> new OrderItemDTO(oi, productService.getById(oi.getProductId())))
                .collect(Collectors.toList());
        return new OrderDTO(order, orderItemDTOS);
    }
}
