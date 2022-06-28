package com.example.ordermgmt.data;

import com.example.ordermgmt.domain.color.BlueColorImpl;
import com.example.ordermgmt.domain.color.RedColorImpl;
import com.example.ordermgmt.domain.color.YellowColorImpl;
import com.example.ordermgmt.domain.order.Order;
import com.example.ordermgmt.domain.order.OrderLineItem;
import com.example.ordermgmt.domain.shape.CircleShapeImpl;
import com.example.ordermgmt.domain.shape.SquareShapeImpl;
import com.example.ordermgmt.domain.shape.TriangleShapeImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataGenerator {

    public static Order generateSampleOrder(){
        List<OrderLineItem> orderLineItemList = new ArrayList<>();
        OrderLineItem line1 = OrderLineItem.builder()
                .itemShape(new SquareShapeImpl())
                .itemColor(new RedColorImpl())
                .quantity(2).build();
        orderLineItemList.add(line1);

        OrderLineItem line2 = OrderLineItem.builder()
                .itemShape(new SquareShapeImpl())
                .itemColor(new BlueColorImpl())
                .quantity(2).build();
        orderLineItemList.add(line2);

        OrderLineItem line3 = OrderLineItem.builder()
                .itemShape(new SquareShapeImpl())
                .itemColor(new RedColorImpl())
                .quantity(1).build();
        orderLineItemList.add(line3);

        OrderLineItem line4 = OrderLineItem.builder()
                .itemShape(new TriangleShapeImpl())
                .itemColor(new BlueColorImpl())
                .quantity(3).build();
        orderLineItemList.add(line4);

        OrderLineItem line5 = OrderLineItem.builder()
                .itemShape(new TriangleShapeImpl())
                .itemColor(new YellowColorImpl())
                .quantity(1).build();
        orderLineItemList.add(line5);

        OrderLineItem line6 = OrderLineItem.builder()
                .itemShape(new CircleShapeImpl())
                .itemColor(new RedColorImpl())
                .quantity(2).build();
        orderLineItemList.add(line6);

        Order order = Order.builder().orderNumber("#001").customerName("Tom Cat").address("Street 1")
                .dueDate(new Date()).orderLineItemList(orderLineItemList).build();
        return order;
    }
}
