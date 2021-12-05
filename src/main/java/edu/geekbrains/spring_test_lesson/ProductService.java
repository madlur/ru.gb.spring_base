package edu.geekbrains.spring_test_lesson;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {

    @Autowired
    private ProductDaoImpl productDaoImpl;

    public void setProductDaoImpl(ProductDaoImpl productDaoImpl) {
        this.productDaoImpl = productDaoImpl;
    }

    public List<Product> getStartPage() {
        return productDaoImpl.findAll();
    }

    public void changeQuantity(Long productId, Integer delta) {

        Product product = productDaoImpl.findById(productId);
        if (product.getQuantity() > 0) {
            int quantityNew = product.getQuantity() + delta;
            product.setQuantity(quantityNew);
            productDaoImpl.update(product);
        } else if (product.getQuantity() == 0 && delta > 0) {
            int quantityNew = product.getQuantity() + delta;
            product.setQuantity(quantityNew);
            productDaoImpl.update(product);
        }
    }

    public void deleteById(Long id) {
        productDaoImpl.deleteById(id);
    }

    public void addProduct(Product product) {

        List<Product> productList = productDaoImpl.findAll();
        for (Product prod : productList) {
            if(product.getTitle().equals(prod.getTitle())) {
                prod.setDescription(product.getDescription());
                prod.setPrice(product.getPrice());
                prod.setQuantity(product.getQuantity());
                productDaoImpl.update(prod);
                return;
            }
        }
            productDaoImpl.save(product);

    }

}
