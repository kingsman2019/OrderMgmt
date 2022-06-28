package com.example.ordermgmt.domain.order;

import com.example.ordermgmt.domain.color.IColor;
import com.example.ordermgmt.domain.shape.IShape;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Order line item in each order
 */
@Data
@Builder
@AllArgsConstructor
public class OrderLineItem {
    private IShape itemShape;

    private IColor itemColor;

    private int quantity;
}
