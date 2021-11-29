package hibernate_example_2_hw;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("hibernate_example_2_hw");
        ProductService productService = context.getBean(ProductService.class);
        CustomerService customerService = context.getBean(CustomerService.class);

//          Products

        for (Product p : productService.findAll()) {
            System.out.println(p);
        }
        System.out.println(productService.findById(2L));
        System.out.println(productService.findCustomersByProductId(2L));
        System.out.println(productService.findAll());


//          Customers

        for (Customer c : customerService.findAll()) {
            System.out.println(c);
        }
        System.out.println(customerService.findById(1L));
        System.out.println(customerService.findProductsByCustomerId(3L));
        System.out.println(customerService.findAll());


        context.close();
    }
}
