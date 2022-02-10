package edu.geekbrains.controllers;


import edu.geekbrains.converters.ProductConverter;
import edu.geekbrains.dto.ProductDto;
import edu.geekbrains.entities.Product;
import edu.geekbrains.exceptions.ResourceNotFoundException;
import edu.geekbrains.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductConverter productConverter;


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
        return productService.findAll(title, minPrice, maxPrice, page).map(p -> productConverter.entityToDto(p));
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
        return productConverter.entityToDto(product);
    }

    @PostMapping
    public ProductDto saveNewProduct(@RequestBody ProductDto productDto) {
        Product product = productConverter.dtoToEntity(productDto);
        product = productService.save(product);
        return productConverter.entityToDto(product);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        Product product = productService.update(productDto);
        return productConverter.entityToDto(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }

}
