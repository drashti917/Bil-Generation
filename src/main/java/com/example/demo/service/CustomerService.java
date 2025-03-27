package com.example.demo.service;


import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository custmerRepo;

    public Customer saveCust(Customer custmer) {
        try {
            return custmerRepo.save(custmer);
        } catch (Exception e) {
            System.out.println("not add");
            return null;
        }
    }

}
