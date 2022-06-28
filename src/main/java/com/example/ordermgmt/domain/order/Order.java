package com.example.ordermgmt.domain.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Order entity
 */
@Data
@Builder
@AllArgsConstructor
public class Order {

    private String orderNumber;

    private String customerName;

    private String address;

    private Date dueDate;

    private List<OrderLineItem> orderLineItemList = new ArrayList<>();
}
