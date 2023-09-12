package com.dayan.restaurant.controller;

import com.dayan.restaurant.model.Product;
import com.dayan.restaurant.service.ProductService;
import com.dayan.restaurant.view.ProductView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    @JsonView(ProductView.Index.class)
    public Iterable<Product> getProducts(){
        return productService.getProducts();
    }
}
