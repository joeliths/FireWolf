package com.example.demo.controllers;



import com.example.demo.entities.PendingOrder;
import com.example.demo.models.PendingOrderModel;
import com.example.demo.repositories.PendingOrderRepository;
import com.example.demo.services.CustomerService;
import com.example.demo.services.PendingOrderService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
public class PendingOrderController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PendingOrderService pendingOrderService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PendingOrderController.class);



    @PostMapping("/pendingorder")
    public void addPendingOrder(@RequestBody PendingOrderModel pendingOrderModel) {
        LOGGER.info("Someone just called me!");
        throw new RuntimeException();
        //pendingOrderService.addPendingOrder(pendingOrderModel);
        //PendingOrder pendingOrder = modelMapper.map(pendingOrderModel,PendingOrder.class);


    }



}
