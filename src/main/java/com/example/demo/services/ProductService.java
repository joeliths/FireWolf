package com.example.demo.services;

import com.example.demo.entities.Product;
import com.example.demo.models.ProductModel;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.resource.spi.EISSystemException;

//TODO:Alot in this class
@Service
public class ProductService {
    final private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public String addProduct(ProductModel productModel){

        Product product = new Product();
        product.setName(productModel.getName());
        product.setDescription(productModel.getDescription());
        try{
            productRepository.save(product);
            return product.getUuid().toString();
        }catch (Exception e){
            return "something wrong";
        }

    }


}
