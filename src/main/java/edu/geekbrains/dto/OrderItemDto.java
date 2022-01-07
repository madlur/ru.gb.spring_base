package edu.geekbrains.dto;


import edu.geekbrains.entities.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OrderItemDto {

    private Long productId;
    private String productTitle;
    private int quantity;
    private BigDecimal pricePerProduct;
    private int price;

    public OrderItemDto(Product product) {
        this.productId = product.getId();
        this.productTitle = product.getTitle();
        this.quantity = 1;
        this.pricePerProduct = product.getPrice();
        this.price = product.getPrice().intValue();
    }

    public void changeQuantity (int delta) {
        this.quantity += delta;
        this.price = this.pricePerProduct.intValue() * this.quantity;
    }
}
