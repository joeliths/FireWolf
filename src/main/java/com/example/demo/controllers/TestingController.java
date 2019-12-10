package com.example.demo.controllers;

import com.example.demo.Mapper.Convert;
import com.example.demo.entities.InventoryProduct;
import com.example.demo.entities.PendingOrder;
import com.example.demo.entities.Vendor;
import com.example.demo.models.InventoryProductModel;
import com.example.demo.models.VendorModel;
import com.example.demo.models.pendingorder.PendingOrderModel;
import com.example.demo.models.pendingorder.PendingOrderRequestModel;
import com.example.demo.repositories.InventoryProductRepository;
import com.example.demo.repositories.PendingOrderRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.reflect.annotation.ExceptionProxy;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

@Controller
public class TestingController {
    Convert convert = new Convert();

    @Autowired
    PendingOrderRepository pendingOrderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    VendorRepository vendorRepository;

    @GetMapping(path = "/pontustest",
            consumes = "application/json",
            produces = "application/json")
    @ResponseBody
    public ResponseEntity getPendingOrder(@RequestBody PendingOrderModel pendingOrderModel) {
        try {
            System.out.println("yeah bruh first step done");

            PendingOrder in = convert.lowAccessConverter(pendingOrderModel, PendingOrder.class);
            System.out.println(in.getUuid().toString());
            PendingOrder dbResponse = pendingOrderRepository.findFirstByUuid(in.getUuid().toString());
            PendingOrderModel outModel = convert.lowAccessConverter(dbResponse, PendingOrderModel.class);
            System.out.println("got here test 3");
            return new ResponseEntity<>(outModel, HttpStatus.OK);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }

    @GetMapping(path = "/pontustest2",
            consumes = "application/json",
            produces = "application/json")
    @ResponseBody
    public ResponseEntity getFirstPendingOrder(@RequestBody PendingOrderModel pendingOrderModel) {
        try {
            PendingOrder pendingOrder = pendingOrderRepository.getTesting();
            System.out.println(pendingOrder.getUuid());
            System.out.println(pendingOrder.toString());
            PendingOrderModel answerModel = convert.lowAccessConverter(pendingOrder, PendingOrderModel.class);
            System.out.println("third reached");
            answerModel.getCustomer().setPendingOrders(null);
            return new ResponseEntity<>(answerModel, HttpStatus.OK);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
      return new ResponseEntity(HttpStatus.I_AM_A_TEAPOT);
    }

    @GetMapping(path = "/pontustestvendor",
            consumes = "application/json",
            produces = "application/json")
    @ResponseBody
    public ResponseEntity getinventoryProductsByVendor(@RequestBody VendorModel vendorModel) {
        try {
            Set<InventoryProduct> responseEntities = vendorRepository.getInventoryProductsOfAStoreOfAVendor(vendorModel.getUuid());
            Set<InventoryProductModel> responseModels = new HashSet<>();
            for(InventoryProduct product:responseEntities){
                InventoryProductModel thisIteration = convert.lowAccessConverter(product, InventoryProductModel.class);
                responseModels.add(thisIteration);
            }

            System.out.println("third reached");
            return new ResponseEntity<>(responseModels, HttpStatus.OK);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.I_AM_A_TEAPOT);
    }

}
