package hibernate_example_2_hw;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue
    @Column (name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;

    @Column(name = "description")
    private String description;

    @Column(name = "quantity")
    private int quantity;

    @ManyToMany
    @JoinTable(
            name = "customers_products",
            joinColumns = @JoinColumn (name = "product_id"),
            inverseJoinColumns = @JoinColumn (name = "customer_id")
    )
    List<Customer> customers;


    public Product() {
    }

    public Product(Long id, String title, int price, String description, int quantity) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
