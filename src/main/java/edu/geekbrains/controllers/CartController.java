package edu.geekbrains.controllers;

import edu.geekbrains.converters.ProductConverter;
import edu.geekbrains.entities.Cart;
import edu.geekbrains.entities.Product;
import edu.geekbrains.exceptions.ResourceNotFoundException;
import edu.geekbrains.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartController {

    private final ProductService productService;
    private final ProductConverter productConverter;
    private final Cart cart;


    @GetMapping
    public List<Product> showCart() {
        return cart.getProductList();
    }

    @GetMapping("/{id}")
    public List<Product> addProductToCart(@PathVariable Long id) {
        cart.addProductToCart(productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id)));
        return cart.getProductList();
    }

    @DeleteMapping("/{id}")
    public List<Product> deleteProductFromCart(@PathVariable Long id) {
        cart.deleteFromCart(id);
        return cart.getProductList();
    }
}
