package ru.filin.HavachMVC.service.orderManagement.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.filin.HavachMVC.constants.OrderStatus;
import ru.filin.HavachMVC.constants.PaymentStatus;
import ru.filin.HavachMVC.constants.PaymentType;
import ru.filin.HavachMVC.controller.DTO.Message;
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

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    public OrderServiceImpl(CartServiceImpl cartService, OrderRepository orderRepository, ProductService productService) {
        this.cartService = cartService;
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    public long makeOrder(long userId,
                          String phone,
                          String address,
                          String paymentType,
                          Message message
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


        validateQuantity(cartMap, message);
        if (message.getString().length() > 0) {
            return -1;
        }

        long orderId = saveOrderAndOrderItems(order, cartMap);
        logger.info("new order has bean created orderId=" + orderId);
        cartService.purgeCartByUser(userId);
        return orderId;
    }

    private void validateQuantity(Map<CartItem, Product> cartMap, Message massage) {
        for (Map.Entry<CartItem, Product> entry : cartMap.entrySet()) {
            int quantity = entry.getKey().getQuantity();
            int stock = entry.getValue().getStock();
            if (stock < quantity) {
                logger.info("not enough items in stock product=" + entry.getValue().getId() + " product name: " + entry.getValue().getName() + "quantity" + entry.getKey().getQuantity() +
                        " stock=" + entry.getValue().getStock());
                massage.setString(massage.getString() + entry.getValue().getName() + " is not available (" + entry.getValue().getStock() + ")");
            }
        }
    }

    private long saveOrderAndOrderItems(Order order, Map<CartItem, Product> cartMap) {
        return orderRepository.saveOrderAndOrderItems(order, cartMap);
    }


    public OrderDTO getOrderByUserIdAndOrderId(long userId, long orderId) {
        Order order = orderRepository.findOrderByIdAndUserID(orderId, userId);
        List<OrderItemFull> orderItems = orderRepository.findOrderItemsByOrderId(orderId);
        List<OrderItemDTO> orderItemDTOS = orderItems.stream()
                .map(oi -> new OrderItemDTO(oi, productService.getById(oi.getProductId())))
                .collect(Collectors.toList());
        logger.info("get order by userId=" + userId + "and orderId=" + orderId);
        return new OrderDTO(order, orderItemDTOS);
    }

    public List<Order> getOrderByUser(long id) {
        List<Order> orders = orderRepository.getAll();
        logger.info("get orders by user" + orders.stream().map(Order::getId).collect(Collectors.toList()).toString());
        return orders.stream()
                .filter(order -> order.getUserId() == id)
                .collect(Collectors.toList());
    }

    public List<Order> getOrders() {
        List<Order> orders = orderRepository.getAll();
        logger.info("get orders by admin " + orders.stream().map(Order::getId).collect(Collectors.toList()).toString());
        return orders;
    }


    public void changeOrderStatus(long id, String orderStatus) {
        logger.info("order [orderId=" + id + "change status to " + orderStatus);
        orderRepository.changeOrderStatus(id, OrderStatus.valueOf(orderStatus));
    }
}
