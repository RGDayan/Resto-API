package com.dayan.restaurant.service;

import com.dayan.restaurant.model.Product;
import com.dayan.restaurant.model.heritages.products.Beverage;
import com.dayan.restaurant.repository.ProductRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
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
        List<Product> products = (List<Product>) productRepository.findAll();
        if (products.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attention : aucun produit n'a été trouvé.");

        List<Product> existingProducts = new ArrayList<>();
        for (Product product :
                products) {
            if (!product.isDeleted)
                existingProducts.add(product);
        }

        return existingProducts;
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }


}
