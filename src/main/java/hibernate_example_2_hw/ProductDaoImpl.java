package hibernate_example_2_hw;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private SessionFactoryUtils factory;

    public void setFactory(SessionFactoryUtils factory) {
        this.factory = factory;
    }

    @Override
    public Product findById(Long id) {
        try(Session session = factory.getSession()) {
            session.beginTransaction();
            return session.get(Product.class, id);
        }
    }

    @Override
    public List<Product> findAll() {
        try(Session session = factory.getSession()) {
            session.beginTransaction();
            List<Product> productList = session.createQuery("FROM Product",Product.class).getResultList();
            session.getTransaction().commit();
            return productList;
        }
    }

    @Override
    public List<Customer> findCustomersByProductId(Long id) {
        try(Session session = factory.getSession()) {
            session.beginTransaction();
            List<Customer> customerList = session.createNativeQuery("SELECT * FROM customers c JOIN customers_products ON c.id = customers_products.customer_id WHERE product_id = :id", Customer.class)
                    .setParameter("id", id)
                    .getResultList();
            session.getTransaction().commit();
            return customerList;
        }
    }
}
