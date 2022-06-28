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
import org.apache.commons.collections4.CollectionUtils;

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

    private List<OrderLineItem> aggregatedOrderLines;

    public List<OrderLineItem> getAggregatedOrderLines() {
        initAggregatedOrderLines();
        return aggregatedOrderLines;
    }

    private void initAggregatedOrderLines() {
        if (CollectionUtils.isEmpty(aggregatedOrderLines)) {
            aggregatedOrderLines = CommonHelper.aggregateOrderLines(order.getOrderLineItemList());
        }
    }

    /**
     * Print order line structure
     */
    protected void printOrderLines() {
        Map<String, OrderLineItem> orderLineItemMap = CommonHelper.populateOrderLineMapByShapeAndColor(
                getAggregatedOrderLines());

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
    protected BigDecimal calcShapePriceTotal() {
        BasePriceCalculator calculator = new ShapePriceCalculator();
        return calculator.calcPrice(getAggregatedOrderLines());
    }

    /**
     * Calculate the total price of color based pricing
     * @return BigDecimal
     */
    protected BigDecimal calcColorPriceTotal() {
        BasePriceCalculator calculator = new ColorPriceCalculator();
        return calculator.calcPrice(getAggregatedOrderLines());
    }

    /**
     * Print report for each dedicated type of report implementation
     */
    abstract public void printReport();
}
