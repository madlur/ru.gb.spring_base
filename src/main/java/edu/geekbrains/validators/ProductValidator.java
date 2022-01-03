package edu.geekbrains.validators;

import edu.geekbrains.dto.ProductDto;
import edu.geekbrains.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {

    public void validate(ProductDto productDto) {
        List<String> errors = new ArrayList<>();
        if (productDto.getPrice().intValue() < 1) {
            errors.add ("Цена продукта не может быть меньше 1");
        }
        if (productDto.getTitle().isBlank()) {
            errors.add ("ЦПродукт не может иметь пустое название");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
