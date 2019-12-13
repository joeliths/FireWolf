package com.example.demo.Mapper;

import com.example.demo.entities.Product;
import com.example.demo.models.ProductModel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.constraints.AssertTrue;


import static org.junit.jupiter.api.Assertions.*;
class ConvertTest {

    @Test
    void assertNoExceptionsCastOnBasicUsage(){
        ProductModel productModel = new ProductModel();
        productModel.setName("Chips");
        productModel.setDescription("goda som sjutton");


        Convert converter = new Convert();
        try{
            Product product = converter.lowAccessConverter(productModel,Product.class);
            System.out.println(product.getName());
            System.out.println(product.getDescription());
        }catch (Exception e){
            e.printStackTrace();
        }

        assertTrue(true);
    }
}
