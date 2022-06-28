package com.example.ordermgmt.service.calc;

import com.example.ordermgmt.domain.order.OrderLineItem;
import com.example.ordermgmt.util.CommonHelper;

import java.math.BigDecimal;

/**
 * Calculator for shape based price
 */
public class ShapePriceCalculator extends BasePriceCalculator{
    @Override
    public BigDecimal getUnitPrice(OrderLineItem orderLineItem) {
        return CommonHelper.getBasicPriceOfShape(orderLineItem);
    }
}
