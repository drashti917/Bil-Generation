package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AdminMessagingService adminMessagingService;

    public Product prodSave(Product product) {
        return productRepository.save(product);
    }

    public void updateStockAndCheckTherseHold(int id, int quantity, int newStock) {
        int threshold = 10;
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not available"));

        if (product.getQuantity() < quantity) {
            System.out.println("insuffient stock");
            return;
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);

        if (product.getQuantity() < threshold) {
            product.setQuantity(product.getQuantity() + newStock);
            productRepository.save(product);
            System.out.println("new stock adeed");

        } else {
            System.out.println("stock updated successfully");
        }
    }

    String filePath="product_report.csv";
    public void generateBil(String filePath,String email) {
        List<Product> products = productRepository.findAll();

        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            String Headers[] = {"pid", "pname", "quantity"};
            writer.writeNext(Headers);
            for (Product product : products) {
                String[] data = {
                        String.valueOf(product.getPid()),
                        product.getPname(),
                        String.valueOf(product.getQuantity())};
                writer.writeNext(data);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error writing CSV file: " + e.getMessage(), e);
        }
        System.out.println("CSV report generated successfully at " + filePath);

        adminMessagingService.sendEmailWithAttachment(filePath);
    }
    }

