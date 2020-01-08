package com.example.demo.controllers;

import com.example.demo.entities.helperclasses.MyUUID;
import com.example.demo.models.pendingorder.PendingOrderRequestModel;
import com.example.demo.services.PendingOrderService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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
    public ResponseEntity addPendingOrder(@RequestBody PendingOrderRequestModel pendingOrder, Principal principal) {
        return ResponseEntity.status(CREATED).body(pendingOrderService.addPendingOrder(pendingOrder, principal.getName()));
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity deletePendingOrder(@PathVariable String uuid, Principal principal){
        System.out.println(principal.getName());
        pendingOrderService.deletePendingOrder(uuid, principal.getName());
        return ResponseEntity.status(204).build();
    }

    @GetMapping("{uuid}")
    public ResponseEntity getPendingOrder(@PathVariable String uuid, Principal principal){
        return ResponseEntity.ok(pendingOrderService.getPendingOrderByUuid(uuid, principal.getName()));
    }












}
