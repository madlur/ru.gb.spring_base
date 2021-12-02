package edu.geekbrains.services;


import edu.geekbrains.entities.Product;
import edu.geekbrains.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).get();
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }


    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void findByPriceBetween(Integer min, Integer max) {
        productRepository.findAllByPriceBetween(min, max);
    }

    @Transactional
    public void changeQuantity(Long productId, Integer delta) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("ERROR")); //ToDo доделать обработку ошибок
        product.setQuantity((product.getQuantity()) + delta);
    }


}
