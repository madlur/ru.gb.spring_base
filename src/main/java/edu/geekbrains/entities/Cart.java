package edu.geekbrains.entities;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Data
@NoArgsConstructor
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Cart {

    private List<Product> productList;

    @PostConstruct
    private void init() {
        productList = new ArrayList<>();
    }

    public void addProductToCart(Product product) {
        productList.add(product);
    }


    public List<Product> getProductList() {
        return Collections.unmodifiableList(productList);
    }

    public void deleteFromCart(Long id) {
        Product p = productList.stream().filter(product -> product.getId().equals(id)).findFirst().get();
        int index = productList.indexOf(p);
        productList.remove(index);
    }
}
