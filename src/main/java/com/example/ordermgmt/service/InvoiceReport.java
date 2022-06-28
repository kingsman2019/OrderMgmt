package com.example.ordermgmt.service;

import com.example.ordermgmt.domain.order.OrderLineItem;
import com.example.ordermgmt.util.CommonHelper;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Invoice report implementation
 */
@Slf4j
public class InvoiceReport extends BaseReport{

    @Override
    public void printReport() {
        printOrderLines();
        printShapePrice();
        printColorPrice();
    }

    /**
     *  Print shape based price
     */
    private void printShapePrice(){
        Map<String, List<OrderLineItem>> orderLineMap = CommonHelper.classifyOrderLinesByShape(super.getAggregatedOrderLines());

        orderLineMap.forEach((k,v) -> {
            BigDecimal unitPrice = Objects.requireNonNull(v.stream().findFirst().orElse(null)).getItemShape().getBasicPrice();
            int totalQuantity = v.stream().map(OrderLineItem::getQuantity).reduce(0,Integer::sum);
            BigDecimal totalPrice = v.stream().map(item -> item.getItemShape().getBasicPrice()
                            .multiply(new BigDecimal(item.getQuantity())))
                            .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
            log.info("{} has quantity of {}, basic price of each unit is {}, total basic price is {}", k, totalQuantity, unitPrice, totalPrice);
            });
    }

    /**
     *  Print color based price
     */
    private void printColorPrice(){
        Map<String, List<OrderLineItem>> orderLineMap = CommonHelper.classifyOrderLinesByShape(super.getAggregatedOrderLines());

        orderLineMap.forEach((k,v) -> {
            BigDecimal unitPrice = Objects.requireNonNull(v.stream().findFirst().orElse(null)).getItemColor().getAdditionalPrice();
            int totalQuantity = v.stream().map(OrderLineItem::getQuantity).reduce(0,Integer::sum);
            BigDecimal totalPrice = v.stream().map(item -> item.getItemColor().getAdditionalPrice()
                            .multiply(new BigDecimal(item.getQuantity())))
                    .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
            log.info("{} has quantity of {}, additional price of each unit is {}, total additional price is {}", k, totalQuantity, unitPrice, totalPrice);
        });
    }

}
