package com.example.OrderAssignment.model;

import lombok.Data;

@Data
public class Item {
    private String itemId;
    private String name;
    private String company;
    private String description;
    private String imageUrl;
}
