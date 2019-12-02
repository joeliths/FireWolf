package com.example.demo.controllers;

import com.example.demo.entities.helperclasses.MyUUID;
import com.example.demo.models.pendingorder.PendingOrderRequestModel;
import com.example.demo.services.PendingOrderService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("/pending-orders")
public class PendingOrderController {

    private final PendingOrderService pendingOrderService;

    public PendingOrderController(PendingOrderService pendingOrderService) {
        this.pendingOrderService = pendingOrderService;
    }

    @PostMapping
    public ResponseEntity addPendingOrder(@RequestBody PendingOrderRequestModel pendingOrder) {
        pendingOrderService.addPendingOrder(pendingOrder);
        return ResponseEntity.status(CREATED).build();
    }

    @GetMapping("/customer")
    public ResponseEntity getPendingOrdersForCustomer(@PathVariable String userName) {
        return ResponseEntity.ok(pendingOrderService.getPendingOrdersForCustomer(userName));
    }

    @GetMapping("/store")
    public ResponseEntity getPendingOrdersForStore(@PathVariable MyUUID storeUUID) {
        return ResponseEntity.ok(pendingOrderService.getPendingOrdersForStore(storeUUID));
    }

    @PutMapping
    public ResponseEntity updatePendingOrder(@PathVariable MyUUID pendingOrderUUID,
                                             @RequestBody PendingOrderRequestModel pendingOrder) {
        pendingOrderService.updatePendingOrder(pendingOrder);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity deletePendingOrder(@PathVariable MyUUID pendingOrderUUID) {
        pendingOrderService.deletePendingOrder(pendingOrderUUID);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity checkoutPendingOrder(@PathVariable MyUUID pendingOrderUUID) {
        pendingOrderService.checkoutPendingOrder(pendingOrderUUID);
        return ResponseEntity.ok().build();
    }









}
