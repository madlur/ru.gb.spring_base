package hibernate_example_2_hw;

import java.util.List;

public interface CustomerDao {

    Customer findById(Long id);
    List<Customer> findAll();
    List<Product> findProductsByCustomerId(Long id);


}
