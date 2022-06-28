package com.example.ordermgmt.service.print;

import com.example.ordermgmt.domain.order.OrderLineItem;
import com.example.ordermgmt.util.CommonHelper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Printer for color based price
 */
public class ColorPricePrinter extends BasePricePrinter{
    @Override
    public BigDecimal getUnitPrice(OrderLineItem item) {
        return CommonHelper.getAdditionalPriceOfColor(item);
    }

    @Override
    public String getPrintMessageTemplate() {
        return "{} has quantity of {}, additional price of each unit is {}, total additional price is {}";
    }

    @Override
    public Map<String, List<OrderLineItem>> getOrderLineMap(List<OrderLineItem> orderLineItemList) {
        return CommonHelper.classifyOrderLinesByColor(orderLineItemList);
    }
}
