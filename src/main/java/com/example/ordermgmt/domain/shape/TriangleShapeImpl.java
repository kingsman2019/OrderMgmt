package com.example.ordermgmt.domain.shape;

import com.example.ordermgmt.constant.AppConstant;

import java.math.BigDecimal;

/**
 * Triangle shape implementation
 */
public class TriangleShapeImpl implements IShape{
    @Override
    public BigDecimal getBasicPrice() {
        return new BigDecimal(2);
    }

    @Override
    public String getName() {
        return AppConstant.SHAPE_TRIANGLE;
    }
}
