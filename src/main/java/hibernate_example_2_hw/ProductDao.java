package hibernate_example_2_hw;

import java.util.List;

public interface ProductDao {

    Product findById(Long id);
    List<Product> findAll();
    List<Customer> findCustomersByProductId(Long id);

}
