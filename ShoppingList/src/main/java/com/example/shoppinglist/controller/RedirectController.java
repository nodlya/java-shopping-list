package com.example.shoppinglist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectController {
    @GetMapping("/")
    public String index() {
        return "shopping-list";
    }

    @GetMapping("/add")
    public String add() {
        return "add-product";
    }
}