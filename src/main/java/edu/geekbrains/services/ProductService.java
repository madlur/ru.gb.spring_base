package edu.geekbrains.services;


import edu.geekbrains.dto.ProductDto;
import edu.geekbrains.entities.Product;
import edu.geekbrains.exceptions.ResourceNotFoundException;
import edu.geekbrains.repositories.ProductRepository;
import edu.geekbrains.repositories.specifications.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;


    public Page<Product> findAll(String title, BigDecimal min, BigDecimal max, Long page) {
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

        return productRepository.findAll(spec, PageRequest.of((int) (page - 1), 50));
    }


    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }


    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public Product update(ProductDto productDto) {
        Product product = productRepository.findById(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Невозможно обновить продукт, не найден в базе, id: " + productDto.getId()));
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        return product;
    }


}
