package com.example.ordermgmt.domain.color;

import com.example.ordermgmt.constant.AppConstant;

import java.math.BigDecimal;

/**
 * Blue color implementation
 */
public class RedColorImpl implements IColor{
    @Override
    public BigDecimal getAdditionalPrice() {
        return BigDecimal.ONE;
    }

    @Override
    public String getName() {
        return AppConstant.COLOR_RED;
    }
}
