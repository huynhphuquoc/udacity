package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public  class CustomerService {
    public CustomerService() {}
    public void addCustomer(final String email, final String firstName, final String lastName) {
        Data.customers.put(email, new Customer(firstName, lastName, email));
    }

    public Customer getCustomer(final String customerEmail) {
        return Data.customers.get(customerEmail);
    }

    public Collection<Customer> getAllCustomers() {
        return Data.customers.values().stream().toList();
    }

}
