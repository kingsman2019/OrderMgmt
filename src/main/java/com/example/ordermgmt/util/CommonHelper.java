package com.example.ordermgmt.util;

import com.example.ordermgmt.domain.color.IColor;
import com.example.ordermgmt.domain.order.OrderLineItem;
import com.example.ordermgmt.domain.shape.IShape;
import com.example.ordermgmt.exception.BaseException;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Helper of all shared function and procedure
 */
public class CommonHelper {

    /** Generate the map key for order line map, in format of ShapeName:ColorName
     * @param item OrderLineItem
     * return String
     */
    public static String generateMapKey(OrderLineItem item) {
        return String.format("%s:%s", item.getItemShape().getName(), item.getItemColor().getName());
    }

    /**
     * Get shape name from the key format shapeName:colorName
     * @param key String
     * @return String
     */
    public static String getShapeNameFromMapKey(String key) {
        List<String> fieldList = Arrays.asList(key.split(":"));
        if (null != fieldList && fieldList.size() >= 1) {
            return fieldList.get(0);
        } else {
            return "";
        }
    }

    /**
     * Get color name from the key format shapeName:colorName
     * @param key String
     * @return String
     */
    public static String getColorNameFromMapKey(String key) {
        List<String> fieldList = Arrays.asList(key.split(":"));
        if (null != fieldList && fieldList.size() >= 2) {
            return fieldList.get(1);
        } else {
            return "";
        }
    }

    /**
     * Aggregate the order lines,add quantity together for order line with same shape and color
     * @param orderLineItemList List<OrderLineItem>
     * return List<OrderLineItem>
     */
    public static List<OrderLineItem>aggregateOrderLines(List<OrderLineItem> orderLineItemList) {
        List<OrderLineItem> aggregatedOrderLines = new ArrayList<>();
        Map<String, List<OrderLineItem>> orderLineMap = orderLineItemList.stream().collect(Collectors.groupingBy(
                item -> generateMapKey(item), Collectors.toList()));

        orderLineMap.forEach((key, list) -> list.stream().reduce(
                (a, b) -> {
                    OrderLineItem LineItem = OrderLineItem.builder()
                            .itemShape(a.getItemShape()).itemColor(a.getItemColor())
                            .quantity(a.getQuantity() + b.getQuantity()).build();
                    return LineItem;
                }).ifPresent(aggregatedOrderLines::add));

        return aggregatedOrderLines;
    }

    /**
     * Populate the order line map from order line list, with map key of ShapeName:ColorName
     * @param orderLineItemList List<OrderLineItem>
     * return Map<String,OrderLineItem>
     */
    public static Map<String,OrderLineItem> populateOrderLineMapByShapeAndColor(List<OrderLineItem> orderLineItemList) {
        Map<String, OrderLineItem> orderLineMap = new HashMap<>();
        orderLineMap = orderLineItemList.stream().collect(Collectors.toMap(
                item -> generateMapKey(item), Function.identity(), (k1, k2) -> k2));
        return orderLineMap;
    }

    /**
     * Return order lines with map of shape name and all items of this shape
     * @param orderLineItemList List<OrderLineItem>
     * return List<OrderLineItem>
     */
    public static Map<String,List<OrderLineItem>> classifyOrderLinesByShape(List<OrderLineItem> orderLineItemList) {
        return orderLineItemList.stream().collect(Collectors.groupingBy(item -> item.getItemShape().getName()));
    }

    /**
     * Return order lines with map of color name and all items of this color
     * @param orderLineItemList List<OrderLineItem>
     * return List<OrderLineItem>
     */
    public static Map<String,List<OrderLineItem>> classifyOrderLinesByColor(List<OrderLineItem> orderLineItemList) {
        return orderLineItemList.stream().collect(Collectors.groupingBy(item -> item.getItemColor().getName()));
    }

    /**
     * Get basic price of Shape category
     * @param item OrderLineItem
     * @return BigDecimal
     */
    public static BigDecimal getBasicPriceOfShape(OrderLineItem item) {
        IShape shape = Optional.ofNullable(item.getItemShape()).orElseThrow(() -> new BaseException("Shape is null"));
        return shape.getBasicPrice();
    }

    /**
     * Get additional price of Color category
     * @param item OrderLineItem
     * @return BigDecimal
     */
    public static BigDecimal getAdditionalPriceOfColor(OrderLineItem item) {
        IColor color = Optional.ofNullable(item.getItemColor()).orElseThrow(() -> new BaseException("Color is null"));
        return color.getAdditionalPrice();
    }

}

