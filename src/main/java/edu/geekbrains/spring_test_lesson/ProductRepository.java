package edu.geekbrains.spring_test_lesson;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {

    private List<Product> productsList;

    @PostConstruct
    public void init() {
        productsList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            productsList.add(new Product((long) (i + 1), "Product #" + (i + 1), "Lorem ipsum...",
                    BigDecimal.valueOf(Math.random() * 1000).setScale(2, RoundingMode.HALF_UP), (int) (Math.random() * 10)));
        }
    }

    public List<Product> getProductsList() {
        return Collections.unmodifiableList(productsList);
    }

    public Product getById (Long id) {
        return productsList.stream().filter(product -> product.getId().equals(id)).findFirst().get();
    }

    public void deleteProductById(Long id) {
        Long finalId = id++;
        productsList.removeIf(product -> product.getId().equals(finalId));
    }

    public void changeProductQuantity (Long id, int delta) {
//      productsList.stream().filter(product -> product.getId().equals(id)).peek(product -> product.setQuantity(delta));

      productsList.stream().filter(product -> product.getId().equals(id)).findFirst().get().setQuantity(delta);
    }

}
