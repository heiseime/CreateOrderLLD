package com.example.OrderAssignment.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "inventory", type = "_doc", createIndex = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Inventory {
    private String inventoryId;

    private Item item;

    private Long quantityAvailable;

    public Long getAvailableStock() {
        return this.getQuantityAvailable();
    }
    public void reduceStock(Long n) {
        Long updatedAvailability =  this.getQuantityAvailable() - n;
        this.setQuantityAvailable(updatedAvailability);
    }

    public boolean placeOrder(Long n) {
        if(n > getAvailableStock()) return false;

        reduceStock(n);

        return true;
    }

    public void updateInventory(Long n) {
        this.setQuantityAvailable(this.getQuantityAvailable() + n);
    }
}
