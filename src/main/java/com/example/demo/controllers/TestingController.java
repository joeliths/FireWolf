package com.example.demo.controllers;

import com.example.demo.Mapper.Convert;
import com.example.demo.entities.PendingOrder;
import com.example.demo.models.pendingorder.PendingOrderModel;
import com.example.demo.models.pendingorder.PendingOrderRequestModel;
import com.example.demo.repositories.PendingOrderRepository;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.reflect.annotation.ExceptionProxy;

import java.lang.reflect.InvocationTargetException;
@Controller
public class TestingController {
    @Autowired
    PendingOrderRepository pendingOrderRepository;

    @Autowired
    ProductRepository productRepository;

    @GetMapping(path = "/pontustest",
            consumes = "application/json",
            produces = "application/json")
    @ResponseBody
    public ResponseEntity getPendingOrder(@RequestBody PendingOrderModel pendingOrderModel) {
        try {
            System.out.println("yeah bruh first step done");
            PendingOrder in = Convert.lowAccessConverter(pendingOrderModel, PendingOrder.class);
            PendingOrder pendingOrder = pendingOrderRepository.findFirstByUuid(pendingOrderModel.getUuid());
            return new ResponseEntity<>(pendingOrder, HttpStatus.OK);
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
        finally {
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
            PendingOrderModel answerModel = Convert.lowAccessConverter(pendingOrder, PendingOrderModel.class);
            System.out.println("third reached");
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
}
