package com.example.demo.controllers;

import com.example.demo.models.PendingOrderModel;
import com.example.demo.services.PendingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    PendingOrderService pendingOrderService;

    @PostMapping(path = "/createPendingOrder",
            consumes = "application/json",
            produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> addPendingOrder(@RequestBody PendingOrderModel pendingOrderModel){
        pendingOrderService.addPendingOrder(pendingOrderModel);
        return new ResponseEntity<>( HttpStatus.OK);
    }
//==================================================
    @PostMapping(path = "/checkout",
            consumes = "application/json",
            produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> checkoutPendingOrder(@RequestBody PendingOrderModel pendingOrderModel){
        pendingOrderService.checkoutPendingOrder(pendingOrderModel);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PatchMapping(path = "/updateOrder",
            consumes = "application/json",
            produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> updatePendingOrder(@RequestBody PendingOrderModel pendingOrderModel){
        pendingOrderService.updatePendingOrder(pendingOrderModel);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @DeleteMapping(path = "/deleteOrder",
            consumes = "application/json",
            produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> deletePendingOrder(@RequestBody PendingOrderModel pendingOrderModel){
        pendingOrderService.deletePendingOrder(pendingOrderModel);
        return new ResponseEntity<>( HttpStatus.OK);
    }

}
