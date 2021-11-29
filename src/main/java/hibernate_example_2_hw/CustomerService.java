package hibernate_example_2_hw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    private void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }


    public Customer findById(Long id) {
        return customerDao.findById(id);
    }

    public List<Customer> findAll() {
       return customerDao.findAll();
    }

    public List<Product> findProductsByCustomerId(Long id) {
        return customerDao.findProductsByCustomerId(id);
    }
}



