package com.pinku.interceptor_filter_example.controller;

import com.pinku.interceptor_filter_example.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(value = "/products")
@Slf4j
public class ProductController {

    @GetMapping(value = "/list")
    public List<Product> getProducts(){
        log.info("ProductController::getProducts()");
        return Stream.of(new Product(1,"iphone 12", 100000),
                new Product(2,"iphone 13", 20000),
                new Product(3,"iphone 14", 230000)
                ).collect(Collectors.toList());
    }

    @PostMapping(value = "/new")
    public Product createProduct(@RequestBody Product product){
        log.info("ProductController::createProduct()");

        return product;
    }
}
