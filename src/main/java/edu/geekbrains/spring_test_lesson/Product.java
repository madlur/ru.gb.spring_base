package edu.geekbrains.spring_test_lesson;

import java.math.BigDecimal;

public class Product {

    private Long id;
    private String name;
    private String description;
    private BigDecimal cost;
    private int quantity;

    public Product() {
    }

    public Product(Long id, String name, String description, BigDecimal cost, int quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
