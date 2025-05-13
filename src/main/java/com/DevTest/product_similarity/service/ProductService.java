package com.DevTest.product_similarity.service;

import com.DevTest.product_similarity.model.Product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;


@Service
public class ProductService {
    
    @Bean
    public Executor taskExecutor() {
        return java.util.concurrent.Executors.newCachedThreadPool();
    }

    @Async
    public CompletableFuture<Product> fetchProductById(String id) {
        try {
            Product product = restTemplate.getForObject("http://localhost:3001/product/" + id, Product.class);
            return CompletableFuture.completedFuture(product);
        } catch (Exception e) {
            logger.warn("Failed async fetch for product {}: {}", id, e.getMessage());
            return CompletableFuture.completedFuture(null);
        }
    }

    private final RestTemplate restTemplate;

    public ProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public List<Product> getSimilarProducts(String productId) {
        String[] ids;
    
        try {
            ids = restTemplate.getForObject("http://localhost:3001/product/" + productId + "/similarids", String[].class);
        } catch (Exception e) {
            logger.error("Error fetching similar IDs for product {}: {}", productId, e.getMessage());
            return List.of();
        }
    
        if (ids == null || ids.length == 0) return List.of();
    
        List<CompletableFuture<Product>> futures = new ArrayList<>();
    
        for (String id : ids) {
            futures.add(fetchProductById(id));
        }
    
        List<Product> products = futures.stream()
            .map(CompletableFuture::join)
            .filter(p -> p != null)
            .toList();
    
        return products;
    }

     

   
}