package ru.filin.HavachMVC.controller.orderManagement.impl;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.filin.HavachMVC.controller.DTO.OrderDTO;
import ru.filin.HavachMVC.model.userManagement.entities.User;
import ru.filin.HavachMVC.service.orderManagement.impl.OrderServiceImpl;

@Controller
@RequestMapping("/order")
public class OrderController {

    private OrderServiceImpl orderService;

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @GetMapping({"orderId"})
    public String getOrder(@AuthenticationPrincipal User user, @PathVariable long orderId, Model model) {
        OrderDTO orderDTO = orderService.getOrderByUserIdAndOrderId(user.getId(), orderId);
        model.addAttribute("order", orderDTO);
        return "userorder";
    }

    @PostMapping
    public String makeOrder(@AuthenticationPrincipal User user,
                            @RequestParam String phone,
                            @RequestParam String address,
                            @RequestParam String paymentType) {
        long orderId = 0;
        if (!(StringUtils.isEmpty(phone) || StringUtils.isEmpty(address) || StringUtils.isEmpty(paymentType))) {
            orderId = orderService.makeOrder(user.getId(), phone, address, paymentType);
        }

        return "redirect:/order/" + orderId;
    }
}
