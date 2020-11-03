package com.example.OrderAssignment.Factory;

import com.example.OrderAssignment.Inventory.ShirtInventory;
import com.example.OrderAssignment.model.Inventory;

public class InventoryFactory {
    public Inventory getInventory(String itemId) {
        switch (itemId) {
            case("shirt_id") :
                return new ShirtInventory();
            default:
                return null;
        }
    }
}
