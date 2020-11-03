package com.example.OrderAssignment.dto;

import lombok.Data;

import java.util.Map;

@Data
public class CreateOrder {
    //ToDo: can extract from user token, for simplicity taking it in request
    private String userId;

    private Long amount;

    private Map<String, Long> items;

    private String address;
}
