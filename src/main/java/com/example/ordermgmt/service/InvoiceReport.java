package com.example.ordermgmt.service;

import com.example.ordermgmt.service.print.BasePricePrinter;
import com.example.ordermgmt.service.print.ColorPricePrinter;
import com.example.ordermgmt.service.print.ShapePricePrinter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * Invoice report implementation
 */
@Slf4j
public class InvoiceReport extends BaseReport{

    @Override
    public void printReport() {
        printOrderLines();
        printShapeWithPrice();
        printColorWithPrice();
        printTotalPrice();
    }

    /**
     *  Print shape based price
     */
    private void printShapeWithPrice() {
        BasePricePrinter pricePrinter = new ShapePricePrinter();
        pricePrinter.printPrice(super.getAggregatedOrderLines());
    }

    /**
     *  Print color based price
     */
    private void printColorWithPrice(){
        BasePricePrinter pricePrinter = new ColorPricePrinter();
        pricePrinter.printPrice(super.getAggregatedOrderLines());
    }

    private void printTotalPrice() {
        BigDecimal totalPrice = calcShapePriceTotal().add(calcColorPriceTotal());
        log.info("Total price is {}", totalPrice);
    }
}
