package com.example.ordermgmt.service.print;

import com.example.ordermgmt.domain.order.OrderLineItem;
import com.example.ordermgmt.util.CommonHelper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Printer for shape based price
 */
public class ShapePricePrinter extends BasePricePrinter{
    @Override
    public BigDecimal getUnitPrice(OrderLineItem item) {
        return CommonHelper.getBasicPriceOfShape(item);
    }

    @Override
    public String getPrintMessageTemplate() {
        return "{} has quantity of {}, basic price of each unit is {}, total basic price is {}";
    }

    @Override
    public Map<String, List<OrderLineItem>> getOrderLineMap(List<OrderLineItem> orderLineItemList) {
        return CommonHelper.classifyOrderLinesByShape(orderLineItemList);
    }
}
