package com.example.ordermgmt.service.print;

import com.example.ordermgmt.domain.order.OrderLineItem;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Base class for printing price of shape based or color based
 */
@Slf4j
public abstract class BasePricePrinter {

    /**
     * Get unit price of either shape or color category
     * @param item OrderLineItem
     * @return BigDecimal
     */
    public abstract BigDecimal getUnitPrice(OrderLineItem item);

    /**
     * Get the message template for printing price
     * @return String
     */
    public abstract String getPrintMessageTemplate();

    /**
     * Get the order line map by each category respectively
     * @return String
     */
    public abstract Map<String, List<OrderLineItem>> getOrderLineMap(List<OrderLineItem> orderLineItemList);

    /**
     * Print the price for either Shape based or Color based
     * @param orderLineItemList List<OrderLineItem>
     */
    public void printPrice(List<OrderLineItem> orderLineItemList) {
        Map<String, List<OrderLineItem>> orderLineMap = getOrderLineMap(orderLineItemList);

        for (Map.Entry<String, List<OrderLineItem>> entry : orderLineMap.entrySet()) {
            String shapeName = entry.getKey();
            List<OrderLineItem> orderLines = entry.getValue();

            OrderLineItem firstItem = Objects.requireNonNull(orderLines.stream().findFirst().orElse(null));
            BigDecimal unitPrice = getUnitPrice(firstItem);
            if (BigDecimal.ZERO.compareTo(unitPrice) >= 0) {
                log.warn("order line {} has invalid unit price of {}", firstItem, unitPrice);
                continue;
            }

            int totalQuantity = orderLines.stream().map(OrderLineItem::getQuantity).reduce(0, Integer::sum);
            BigDecimal totalPrice = orderLines.stream().map(item -> getUnitPrice(item)
                            .multiply(new BigDecimal(item.getQuantity())))
                    .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

            log.info(getPrintMessageTemplate(), shapeName, totalQuantity, unitPrice, totalPrice);
        };
    }

}
