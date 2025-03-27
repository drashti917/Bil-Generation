package com.example.demo.controller;


import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("save")
    public Product prodSave(@RequestBody Product product){
        return productService.prodSave(product);
    }

    @PutMapping("updatestock")
    public void updateStockAndCheckTherseHold(@RequestParam int id,@RequestParam int quantity,@RequestParam int newStock){
        productService.updateStockAndCheckTherseHold(id,quantity,newStock);
    }

    @PostMapping("genrate-Report")
    public String generateCsv(@RequestParam String email,@RequestParam String filePath){
        productService.generateBil(filePath,email);
        return "csv report successfully";
    }
}
