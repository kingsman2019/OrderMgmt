package com.example.ordermgmt.service.calc;

import com.example.ordermgmt.domain.order.OrderLineItem;
import com.example.ordermgmt.domain.shape.IShape;
import com.example.ordermgmt.exception.BaseException;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Calculator for shape based price
 */
public class ShapePriceCalculator extends BasePriceCalculator{
    @Override
    public BigDecimal getUnitPrice(OrderLineItem orderLineItem) {
        IShape shape = Optional.ofNullable(orderLineItem.getItemShape()).orElseThrow(() -> new BaseException("Shape is null"));
        return shape.getBasicPrice();
    }
}
