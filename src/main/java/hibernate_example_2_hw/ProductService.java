package hibernate_example_2_hw;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    private void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public Product findById(Long id) {
        return productDao.findById(id);
    }

    public List<Product> findAll() {
        return productDao.findAll();
    }

    public List<Customer> findCustomersByProductId(Long id) {
        return productDao.findCustomersByProductId(id);
    }

}
