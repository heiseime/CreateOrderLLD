package com.example.OrderAssignment.Controller;

import com.example.OrderAssignment.Service.OrderService;
import com.example.OrderAssignment.dto.CreateOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = "application/json")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/order/create")
    @ResponseBody
    public ResponseEntity<?> createOrder(@RequestBody CreateOrder orderRequest) {
        //ToDo: can have more details in response according to the use case
        String orderId = orderService.createOrder(orderRequest);
        return ResponseEntity.ok(orderId);
    }
}
