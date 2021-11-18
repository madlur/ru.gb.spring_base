package edu.geekbrains.spring_test_lesson;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void changeQuantity(Long productId, Integer delta) {
        Product product = productRepository.getById(productId);
        if (product.getQuantity() != 0) {
            int quantityNew = product.getQuantity() + delta;
            productRepository.changeProductQuantity(productId, quantityNew);
        } else if (product.getQuantity() == 0 && delta != -1) {
            int quantityNew = product.getQuantity() + delta;
            productRepository.changeProductQuantity(productId, quantityNew);
        }


    }

}
