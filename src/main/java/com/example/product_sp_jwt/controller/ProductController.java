package com.example.product_sp_jwt.controller;

import com.example.product_sp_jwt.model.Product;
import com.example.product_sp_jwt.service.product.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        Iterable<Product> products = productService.getAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
