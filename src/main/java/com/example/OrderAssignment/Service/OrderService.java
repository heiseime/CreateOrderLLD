package com.example.OrderAssignment.Service;

import com.example.OrderAssignment.Exception.InvalidInputException;
import com.example.OrderAssignment.Factory.InventoryFactory;
import com.example.OrderAssignment.dto.CreateOrder;
import com.example.OrderAssignment.model.Inventory;
import com.example.OrderAssignment.model.Order;
import com.example.OrderAssignment.model.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Map;

public class OrderService {
    @Autowired
    private InventoryFactory inventoryFactory;

    public String createOrder(CreateOrder orderRequest) {
        Order newOrder = new Order();
        newOrder.setOrderId("1"); //ToDo: random guid for each order
        newOrder.setStatus(OrderStatus.Pending);
        newOrder.setAmount(orderRequest.getAmount());
        newOrder.setAddress(orderRequest.getAddress());

        Map<String, Long>  orderItem = orderRequest.getItems();
        HashSet<String> updatedItems = new HashSet<>();

        //holding items in inventory for this request
        orderItem.forEach((id, quantity) -> {
            Inventory inventory = inventoryFactory.getInventory(id);
            if(inventory.placeOrder(quantity)) updatedItems.add(id);
            else {
                //this will free up the inventory if any of the required item is not available in required quanity.
                resetInventory(newOrder, orderItem, updatedItems);
                throw new InvalidInputException("One of the Item is out of stock");
            }
        });

        boolean isPaymentDone = true; //ToDo: payment service will return with success or failure
        if(isPaymentDone) {
            newOrder.setStatus(OrderStatus.Success);
            return newOrder.getOrderId();
        } else {
            //this will free up the inventory if payment has failed.
            resetInventory(newOrder, orderItem, updatedItems);
            //ToDo: can make more payment failure reason as error message
            throw new InvalidInputException("Payment Failed, please try again after some time");
        }
    }

    private void resetInventory(Order newOrder, Map<String, Long> orderItem, HashSet<String> updatedItems) {
        updatedItems.forEach(updatedItem -> {
            Inventory updatedInventory = inventoryFactory.getInventory(updatedItem);
            updatedInventory.updateInventory(orderItem.get(updatedItem));
        });
        newOrder.setStatus(OrderStatus.Failed);
    }
}
