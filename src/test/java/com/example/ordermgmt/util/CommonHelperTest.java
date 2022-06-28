package com.example.ordermgmt.util;

import com.example.ordermgmt.constant.AppConstant;
import com.example.ordermgmt.data.DataGenerator;
import com.example.ordermgmt.domain.order.Order;
import com.example.ordermgmt.domain.order.OrderLineItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("CommonHelper test")
public class CommonHelperTest {
    private Order order;

    private void initOrderLines() {
        order = DataGenerator.generateSampleOrder();
    }

    @BeforeEach
    public void init() {
        initOrderLines();
    }

    @DisplayName("Aggregate order line")
    @Test
    void testAggregateOrderLineCaseOne(){
        List<OrderLineItem> orderLineItemList = CommonHelper.aggregateOrderLines(order.getOrderLineItemList());
        assertEquals(5, orderLineItemList.size());

        Map<String,OrderLineItem> orderLineItemMap = CommonHelper.populateOrderLineMapByShapeAndColor(orderLineItemList);
        OrderLineItem squareRed = orderLineItemMap.get(AppConstant.SHAPE_SQUARE + ":" + AppConstant.COLOR_RED);
        assertEquals(3,squareRed.getQuantity());

        OrderLineItem triangleBlue = orderLineItemMap.get(AppConstant.SHAPE_TRIANGLE + ":" + AppConstant.COLOR_BLUE);
        assertEquals(3,triangleBlue.getQuantity());
    }
}
