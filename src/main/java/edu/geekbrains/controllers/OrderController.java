package edu.geekbrains.controllers;


import edu.geekbrains.dto.Cart;
import edu.geekbrains.exceptions.AppError;
import edu.geekbrains.services.CartService;
import edu.geekbrains.services.OrderService;
import edu.geekbrains.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final CartService cartService;
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody String username) {
        if(username == null) {
            return new ResponseEntity<>(new AppError("You need to login in store"), HttpStatus.CONFLICT);
        }
        Cart cart = cartService.getCurrentCart();
        orderService.saveNewOrder(cart, username);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

}
