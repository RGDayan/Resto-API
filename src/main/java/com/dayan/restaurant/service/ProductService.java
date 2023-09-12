package com.dayan.restaurant.service;

import com.dayan.restaurant.model.Product;
import com.dayan.restaurant.repository.ProductRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Optional<Product> getProduct(Long id){
        return productRepository.findById(id);
    }

    public Iterable<Product> getProducts(){
        return productRepository.findAll();
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }


}
