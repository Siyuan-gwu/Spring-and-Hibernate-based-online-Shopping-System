package onlineShop.service;

import onlineShop.model.Customer;

public interface CustomerService {

    boolean addCustomer(Customer customer);

    Customer getCustomerByUserName(String userName);

}
