package com.example.demo.services;

import com.example.demo.Mapper.Convert;
import com.example.demo.entities.Product;
import com.example.demo.models.ProductModel;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.resource.spi.EISSystemException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

//TODO:Alot in this class
@Service
public class ProductService {
    Convert convert = new Convert();
    final private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public String addProduct(ProductModel productModel){
        try{
            Product product = convert.lowAccessConverter(productModel, Product.class);
            productRepository.save(product);
            return product.getUuid().toString();
        }catch (Exception e){
            e.printStackTrace();
            return "something wrong";
        }

    }

    //TODO:test method.
    public boolean deleteProduct(ProductModel productModel) {
        //TODO:change to using coneverted to entity.
        productRepository.deleteByUuid(productModel.getUuid());
        return true;
    }


    public Set<ProductModel> getProductsLike(String searchTerm){
        try {

            Set<Product> productEntities = productRepository.findByNameIgnoreCaseContaining(searchTerm);

            Set<ProductModel> productModelList = new HashSet<>();
            for (Product product : productEntities) {
                ProductModel productModel = convert.lowAccessConverter(product, ProductModel.class);
                productModelList.add(productModel);
            }

            return productModelList;
        }catch (Exception e){
            System.out.println("Houston we got a problem in ProductService");
            e.printStackTrace();
            return null;
        }
    }
    public ProductModel getProductByUuid(String uuid){
        Optional<Product> dbResponse = productRepository.findByUuid(uuid);
        if(dbResponse.isEmpty()){
            System.out.println("something");
        }else{

                convert.lowAccessConverter( dbResponse.get(),ProductModel.class);

        }
            return null;
    }
}
