package com.example.ordermgmt.service;

import com.example.ordermgmt.domain.order.Order;
import com.example.ordermgmt.domain.order.OrderLineItem;
import com.example.ordermgmt.service.calc.BasePriceCalculator;
import com.example.ordermgmt.service.calc.ColorPriceCalculator;
import com.example.ordermgmt.service.calc.ShapePriceCalculator;
import com.example.ordermgmt.util.CommonHelper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Base report for all extended report
 */
@Slf4j
public abstract class BaseReport {

    @Getter
    @Setter
    private Order order;

    @Getter
    @Setter
    private List<OrderLineItem> aggregatedOrderLines;

    /**
     * Print order line structure
     */
    protected void printOrderLines() {
        aggregatedOrderLines = CommonHelper.aggregateOrderLines(order.getOrderLineItemList());

        Map<String, OrderLineItem> orderLineItemMap = CommonHelper.populateOrderLineMapByShapeAndColor(aggregatedOrderLines);

        orderLineItemMap.forEach((k, v) -> {
            log.info("{} {} has quantity {}",
                    CommonHelper.getShapeNameFromMapKey(k),
                    CommonHelper.getColorNameFromMapKey(k),
                    v.getQuantity());
        });
    }

    /**
     * Calculate the total price of shape based pricing
     * @return BigDecimal
     */
    private BigDecimal calcShapePriceTotal() {
        BasePriceCalculator calculator = new ShapePriceCalculator();
        return calculator.calcPrice(order.getOrderLineItemList());
    }

    /**
     * Calculate the total price of color based pricing
     * @return BigDecimal
     */
    private BigDecimal calcColorPriceTotal() {
        BasePriceCalculator calculator = new ColorPriceCalculator();
        return calculator.calcPrice(order.getOrderLineItemList());
    }

    /**
     * Print report for each dedicated type of report implementation
     */
    abstract public void printReport();
}
