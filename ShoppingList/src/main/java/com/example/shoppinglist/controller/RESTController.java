package com.example.shoppinglist.controller;

import com.example.shoppinglist.model.Product;
import com.example.shoppinglist.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class RESTController {
    @Autowired
    ProductService productService;

    @GetMapping("/")
    private Iterable<Product> list() {
        return productService.findAllProducts();
    }

    @GetMapping("/{id}")
    private Optional<Product> getById(@PathVariable("id") Long id) {
        return productService.findById(id);
    }

    @PostMapping("/delete/{id}")
    private void delete(@PathVariable("id") Long id) {
        productService.delete(id);
    }

    @PostMapping("/update/{id}")
    private void update(@PathVariable("id") Long id) {
        var product = productService.findById(id);
        productService.update(id, product.get().getName(), product.get().isBought() ? false : true);
    }

    @PostMapping("/add")
    private void addProduct(@RequestBody Product product) {
        productService.save(product.getName());
    }
}