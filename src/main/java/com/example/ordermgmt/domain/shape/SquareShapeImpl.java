package com.example.ordermgmt.domain.shape;

import com.example.ordermgmt.constant.AppConstant;

import java.math.BigDecimal;

/**
 * Square shape implementation
 */
public class SquareShapeImpl implements IShape{
    @Override
    public BigDecimal getBasicPrice() {
        return BigDecimal.ONE;
    }

    @Override
    public String getName() {
        return AppConstant.SHAPE_SQUARE;
    }
}
