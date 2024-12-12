package com.dnd.fbs.services;

import com.dnd.fbs.models.Customer;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    public Customer getCustomer(String username);
    public void updateCustomer(Customer newCus,Integer cid);
    public void createCustomer(Customer customer);
}
