package ru.filin.HavachMVC.controller.orderManagement.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.filin.HavachMVC.controller.DTO.CartConvector;
import ru.filin.HavachMVC.controller.DTO.CartDTO;
import ru.filin.HavachMVC.model.orderManagement.entities.CartItem;
import ru.filin.HavachMVC.model.userManagement.entities.User;
import ru.filin.HavachMVC.model.userManagement.entities.UserContacts;
import ru.filin.HavachMVC.service.orderManagement.impl.CartServiceImpl;
import ru.filin.HavachMVC.service.userManagement.UserContactsService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cart")
public class CartController {

    private CartServiceImpl cartService;
    private UserContactsService userContactsService;

    @Autowired
    public CartController(CartServiceImpl cartService, UserContactsService userContactsService) {
        this.cartService = cartService;
        this.userContactsService = userContactsService;
    }

    @GetMapping
    public String getCartPage(@AuthenticationPrincipal User user, Model model) {
        List<CartItem> cart = cartService.findCartsByUser(user.getId());
        List<CartDTO> cartDTOS = cart.stream().map(c -> CartConvector.toCartDTO(c)).collect(Collectors.toList());
        UserContacts userContacts = userContactsService.getByUserId(user.getId());


        model.addAttribute("cart", cartDTOS);
        model.addAttribute("userContacts", userContacts);

        return "orderPages/cart";
    }

    @PostMapping("/add/")
    public String addNewItem(@AuthenticationPrincipal User user, @RequestParam long productId, @RequestParam int quantity) {
        cartService.saveCartItem(user.getId(), productId, quantity);
        return "redirect:/cart";
    }

}
