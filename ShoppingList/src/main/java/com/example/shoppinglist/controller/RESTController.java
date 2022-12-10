package com.example.shoppinglist.controller;

import com.example.shoppinglist.model.Product;
import com.example.shoppinglist.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RESTController {
    @Autowired
    ProductService productService;

    @GetMapping("/product/")
    private Iterable<Product> list() {
        return productService.findAllProducts();
    }

    @GetMapping("/product/{id}")
    private Optional<Product> getById(@PathVariable("id") Long id) {
        return productService.findById(id);
    }

    @DeleteMapping("/product/{id}")
    private void delete(@PathVariable("id") Long id) {
        productService.delete(id);
    }

    @PutMapping("/product/{id}")
    private void update(@PathVariable("id") Long id) {
        var product = productService.findById(id);
        productService.update(id, product.get().getName(), product.get().isBought() ? false : true);
    }

    @PostMapping("/add")
    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        productService.save(request.getParameter("productName"));
        response.sendRedirect("/add");
    }
}