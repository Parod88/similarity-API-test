package com.DevTest.product_similarity.controller;

import com.DevTest.product_similarity.model.Product;
import com.DevTest.product_similarity.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/{productId}/similar")
    public ResponseEntity<List<Product>> getSimilarProducts(@PathVariable String productId) {
        return ResponseEntity.ok(service.getSimilarProducts(productId));
    }
}