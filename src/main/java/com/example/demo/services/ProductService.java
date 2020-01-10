package com.example.demo.services;

import com.example.demo.Mapper.Convert;
import com.example.demo.entities.Product;
import com.example.demo.models.ProductModel;
import com.example.demo.models.view.PendingOrderProductView;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.resource.spi.EISSystemException;
import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Transactional
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

    public boolean deleteProduct(String uuid) {
        Optional<Product> product = productRepository.findByUuid(uuid);
        if(product.isEmpty())
            throw new EntityNotFoundException("Product with uuid "+uuid+" can not be found");

        productRepository.delete(product.get());
        return true;
    }


    public Set<ProductModel> getProductsLike(String searchTerm){
        if(searchTerm == null) {
            throw new BadRequestException("Missing query param 'name'.");
        }
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
            System.out.println("no matching uuid found");
        }else{
            return convert.lowAccessConverter( dbResponse.get(),ProductModel.class);
        }
            return null;
    }

    public void updateProduct(String uuid, ProductModel inputModel) {
        Optional dbResult = productRepository.findByUuid(uuid);
        if(dbResult.isEmpty()){
            throw new EntityNotFoundException("Product with uuid "+uuid+" not found");
        }else{
//            Product newData = convert.lowAccessConverter(inputModel, Product.class);
            Product retrievedFromDb = (Product)dbResult.get();
            if(inputModel.getName() !=  null){
                retrievedFromDb.setName(inputModel.getName());
            }
            if(inputModel.getDescription() != null){
                retrievedFromDb.setDescription(inputModel.getDescription());
            }
            productRepository.updateProduct(retrievedFromDb);
        }

    }
}
