package com.example.ordermgmt.service.calc;

import com.example.ordermgmt.domain.color.IColor;
import com.example.ordermgmt.domain.order.OrderLineItem;
import com.example.ordermgmt.exception.BaseException;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Calculator for color based price
 */
public class ColorPriceCalculator extends BasePriceCalculator{
    @Override
    public BigDecimal getUnitPrice(OrderLineItem orderLineItem) {
        IColor color = Optional.ofNullable(orderLineItem.getItemColor()).orElseThrow(() -> new BaseException("Color is null"));
        return color.getAdditionalPrice();
    }
}
