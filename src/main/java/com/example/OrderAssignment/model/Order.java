package com.example.OrderAssignment.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.List;

@Data
@Document(indexName = "order", type = "_doc", createIndex = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order implements Serializable {

    private String orderId;

    private String userId;

    private Long amount;

    private List<Item> items;

    private OrderStatus status;

    private String address;
}
