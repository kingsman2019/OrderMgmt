package com.example.ordermgmt.service.calc;

import com.example.ordermgmt.domain.order.OrderLineItem;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
public abstract class BasePriceCalculator {

    /**
     * Return the unit price of each category
     * @param orderLineItem OrderLineItem
     * @return BigDecimal
     */
    abstract public BigDecimal getUnitPrice(OrderLineItem orderLineItem);

    /**
     * Calculate the total price based on order line unit price
     * @param orderLineItemList List<OrderLineItem>
     * @return BigDecimal
     */
    public BigDecimal calcPrice(List<OrderLineItem> orderLineItemList) {
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (OrderLineItem orderLineItem : orderLineItemList) {
            int quantity = orderLineItem.getQuantity();
            if (0 >= quantity) {
                log.debug("orderLineItem {} has invalid quantity", orderLineItem);
                continue;
            }

            BigDecimal unitPrice = getUnitPrice(orderLineItem);
            if (BigDecimal.ZERO.compareTo(unitPrice) >= 0) {
                log.debug("orderLineItem {} has null unit price ", orderLineItem);
                continue;
            }

            BigDecimal price = unitPrice.multiply(new BigDecimal(quantity));
            totalPrice = totalPrice.add(price);
        }

        return totalPrice;
    }
}
