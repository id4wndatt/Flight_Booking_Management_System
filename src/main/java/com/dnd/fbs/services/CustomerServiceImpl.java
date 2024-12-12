package com.dnd.fbs.services;

import com.dnd.fbs.models.Customer;
import com.dnd.fbs.repositories.CustomerRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    CustomerRepositories cu;
    @Override
    public Customer getCustomer(String username) {
        return cu.findCustomerByUser_Username(username);
    }
    public Customer getByID(int id){
        return cu.findCustomerByCustomerID(id);
    }
    @Override
    public void updateCustomer(Customer newCus, Integer cid) {
        Customer cus = cu.findCustomerByCustomerID(cid);
        cus.setAddress(newCus.getAddress());
        cus.setBirthday(newCus.getBirthday());
        cus.setEmail(newCus.getEmail());
        cus.setFullname(newCus.getFullname());
        cus.setNationality(newCus.getNationality());
        cus.setCitizenIdentification(newCus.getCitizenIdentification());
        cus.setPhone(newCus.getPhone());
        cus.setSex(newCus.getSex());
        cu.save(cus);
    }
    public void updateCustomer(Integer cid,String fullname,String address,String birthday,String email,String nationality,String citizen_identification,String phone,String sex) {
        Customer cus = cu.findCustomerByCustomerID(cid);
        cus.setAddress(address);
        cus.setBirthday(birthday);
        cus.setEmail(email);
        cus.setFullname(fullname);
        cus.setNationality(nationality);
        cus.setCitizenIdentification(citizen_identification);
        cus.setPhone(phone);
        cus.setSex(sex);
        cu.save(cus);
    }

    @Override
    public void createCustomer(Customer customer) {
        cu.save(customer);
    }
}
