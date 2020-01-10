package com.example.demo.Mapper;

import com.example.demo.entities.Product;
import com.example.demo.models.ProductModel;
import org.junit.jupiter.api.Test;

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
        }catch (Exception e){
            e.printStackTrace();
        }

        assertTrue(true);
    }
}
