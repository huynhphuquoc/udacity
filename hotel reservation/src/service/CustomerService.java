package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public  class CustomerService {

    private final Map<String, Customer> customers = new HashMap<>();
    public CustomerService() {}
    public void addCustomer(final String email, final String firstName, final String lastName) {
        customers.put(email, new Customer(firstName, lastName, email));
    }

    public Customer getCustomer(final String customerEmail) {
        return customers.get(customerEmail);
    }

    public List<Customer> getAllCustomers() {
        return customers.values().stream().toList();
    }

}
