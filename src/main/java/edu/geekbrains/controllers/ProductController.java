package edu.geekbrains.controllers;


import edu.geekbrains.dto.ProductDto;
import edu.geekbrains.entities.Product;
import edu.geekbrains.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    ProductService productService;

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Page<ProductDto> startPage(
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "minFilter", required = false) BigDecimal minPrice,
            @RequestParam(name = "maxFilter", required = false) BigDecimal maxPrice,
            @RequestParam(name = "p", defaultValue = "1") Long page
    ) {
        if (page < 1) {
            page = 1L;
        }
        return productService.find(title, minPrice, maxPrice, page);
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public void addProduct(@RequestBody ProductDto productDto) {
        productDto.setId(null);
        productService.saveProduct(productDto);
    }

    @PutMapping
    public void updateProduct(@RequestBody ProductDto productDto) {
        productService.saveProduct(productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @GetMapping("/change_quantity")
    public void changeQuantity(@RequestParam Long productId, @RequestParam Integer delta) {
        productService.changeQuantity(productId, delta);
    }
}
