package com.example.ordermgmt.domain.shape;

import com.example.ordermgmt.constant.AppConstant;

import java.math.BigDecimal;

/**
 * Circle shape implementation
 */
public class CircleShapeImpl implements IShape{
    @Override
    public BigDecimal getBasicPrice() {
        return new BigDecimal(3);
    }

    @Override
    public String getName() {
        return AppConstant.SHAPE_CIRCLE;
    }
}
