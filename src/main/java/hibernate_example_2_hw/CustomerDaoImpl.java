package hibernate_example_2_hw;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerDaoImpl implements CustomerDao {

    @Autowired
    private SessionFactoryUtils factory;

    private void setFactory(SessionFactoryUtils factory) {
        this.factory = factory;
    }

    @Override
    public Customer findById(Long id) {
        try(Session session = factory.getSession()) {
            session.beginTransaction();
            return session.get(Customer.class, id);
        }
    }

    @Override
    public List<Customer> findAll() {
        try (Session session = factory.getSession()) {
            session.beginTransaction();
            List<Customer> customerList = session.createQuery("FROM Customer c", Customer.class).getResultList();
            session.getTransaction().commit();
            return customerList;
        }
    }

    @Override
    public List<Product> findProductsByCustomerId (Long id) {
        try (Session session = factory.getSession()) {
            session.beginTransaction();
            List<Product> productList = session.createNativeQuery("SELECT * FROM products p JOIN customers_products ON p.id = customers_products.product_id WHERE customer_id = :id", Product.class)
                    .setParameter("id", id)
                    .getResultList();
            session.getTransaction().commit();
            return productList;
        }
    }
}
