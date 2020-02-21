package onlineShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import onlineShop.dao.CustomerDao;
import onlineShop.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

	public boolean addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		 return customerDao.addCustomer(customer);
	}

	public Customer getCustomerByUserName(String userName) {
		// TODO Auto-generated method stub
		return customerDao.getCustomerByUserName(userName);
	}

}
