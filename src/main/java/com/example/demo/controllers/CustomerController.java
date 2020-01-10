package com.example.demo.controllers;

import com.example.demo.models.pendingorder.PendingOrderResponseModel;
import com.example.demo.services.PendingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    PendingOrderService pendingOrderService;

    @GetMapping("/pending-orders")
    public ResponseEntity<List<PendingOrderResponseModel>> getPendingOrdersForCustomer(Principal principal) {
        return ResponseEntity.ok(pendingOrderService.getPendingOrdersForCustomer(principal.getName()));
    }

}
