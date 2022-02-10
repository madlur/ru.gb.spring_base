package edu.geekbrains.services;

import edu.geekbrains.dto.Cart;
import edu.geekbrains.entities.Order;
import edu.geekbrains.entities.User;
import edu.geekbrains.repositories.OrderRepository;
import edu.geekbrains.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public void saveNewOrder(Cart cart, String username) {
        User user = userRepository.findByUserName(username).get();
        Long userId = user.getId();
        Order order = new Order(cart.getTotalPrice(), user.getAddress(), user.getPhone());
        order.setUser(userRepository.findById(userId).get());
        orderRepository.save(order);
    }

}
