package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.model.Orders;
import com.example.demo.model.Product;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.OrdersRepository;
import com.example.demo.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class OrdersService {

    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Transactional
    public Orders placeOrder(Orders orders){
        Customer customer = customerRepository.findById(orders.getCustomer().getId())
                .orElseThrow(() -> new RuntimeException("Customer not available"));
        Product product =productRepository.findById(orders.getProduct().getPid()).orElseThrow(()-> new RuntimeException("product not avilable"));

        if(product.getQuantity() < orders.getQuantity()){
            throw new RuntimeException("insufficient stock");
        }
        orders.setProduct(product);
        orders.setCustomer(customer);

        double gst= product.getPrice()*0.18;
        double totalprice=(product.getPrice()+gst)* orders.getQuantity();
        orders.setTotalPrice(totalprice);

        product.setQuantity(product.getQuantity()-orders.getQuantity());
        productRepository.save(product);

        orders.setPaymentAmount(totalprice);
        orders.setPaymentStatus("paid");
        orders.setPaymentDate(LocalDate.now());
        ordersRepository.save(orders);

//        twilioService.sendWhatsapp(orders);
//        twilioService.sendSMS(orders);
        return orders;
    }



}
