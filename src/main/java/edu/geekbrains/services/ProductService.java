package edu.geekbrains.services;


import edu.geekbrains.dto.ProductDto;
import edu.geekbrains.entities.Product;
import edu.geekbrains.repositories.ProductRepository;
import edu.geekbrains.repositories.specifications.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<ProductDto> find(String title, BigDecimal min, BigDecimal max, Long page) {
        Specification<Product> spec = Specification.where(null);
        if (min != null) {
            spec = spec.and(ProductSpecification.priceGreaterOrEqualsThan(min));
        }
        if (max != null) {
            spec = spec.and(ProductSpecification.priceLessThanOrEqualsThan(max));
        }
        if (title != null) {
            spec = spec.and(ProductSpecification.nameLike(title));
        }

        return productRepository.findAll(spec, PageRequest.of((int) (page - 1), 20)).map(p -> new ProductDto(p));
    }

    public List<Product> findAll(BigDecimal min, BigDecimal max) {
        return productRepository.findAllByPriceBetween(min, max);
    }

    public ProductDto getProductById(Long id) {
        return productRepository.findById(id).map(p -> new ProductDto(p)).get();
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }


    public void saveProduct(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());

        productRepository.save(product);

    }


    @Transactional
    public void changeQuantity(Long productId, Integer delta) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("ERROR")); //ToDo доделать обработку ошибок
        product.setQuantity((product.getQuantity()) + delta);
    }


}
