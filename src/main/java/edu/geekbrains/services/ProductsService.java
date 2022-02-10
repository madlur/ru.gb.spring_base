package edu.geekbrains.services;


import edu.geekbrains.dto.ProductDto;
import edu.geekbrains.entities.Product;
import edu.geekbrains.exceptions.ResourceNotFoundException;
import edu.geekbrains.repositories.ProductsRepository;
import edu.geekbrains.repositories.specifications.ProductsSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private final ProductsRepository productsRepository;

    public Page<Product> findAll(Integer minPrice, Integer maxPrice, String partTitle, String category, Integer page) {
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductsSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductsSpecifications.priceLessThanOrEqualsThan(maxPrice));
        }
        if (partTitle != null) {
            spec = spec.and(ProductsSpecifications.titleLike(partTitle));
        }
        if (category != null) {
            spec = spec.and(ProductsSpecifications.categoryLike(category));
        }

        return productsRepository.findAll(spec, PageRequest.of(page - 1, 8));
    }

        public static final Function<Product, edu.geekbrains.soap.Product> functionEntityToSoap = pe -> {
        edu.geekbrains.soap.Product p = new edu.geekbrains.soap.Product();
        p.setId(pe.getId());
        p.setTitle(pe.getTitle());
        p.setPrice(pe.getPrice());
        return p;
    };

    public Optional<Product> findById(Long id) {
        return productsRepository.findById(id);
    }

    public edu.geekbrains.soap.Product findByIdSoap(Long id) {
        return productsRepository.findById(id).map(functionEntityToSoap).get();
    }

    public List<edu.geekbrains.soap.Product> getAllProductsSoap() {
        return productsRepository.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        productsRepository.deleteById(id);
    }

    public Product save(Product product) {
        return productsRepository.save(product);
    }

    @Transactional
    public Product update(ProductDto productDto) {
        Product product = productsRepository.findById(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Невозможно обновить продукта, не надйен в базе, id: " + productDto.getId()));
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        return product;
    }
}
