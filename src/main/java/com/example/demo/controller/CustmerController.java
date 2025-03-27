package com.example.demo.controller;


import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("customer")
public class CustmerController {

    @Autowired
    CustomerService custmerService;

    @PostMapping("customersave")
    public Customer save(@RequestBody Customer customer){
        return custmerService.saveCust(customer);
    }
}
