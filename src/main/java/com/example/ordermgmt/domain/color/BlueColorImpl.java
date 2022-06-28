package com.example.ordermgmt.domain.color;

import com.example.ordermgmt.constant.AppConstant;

import java.math.BigDecimal;

/**
 * Blue color implementation
 */
public class BlueColorImpl implements IColor{
    @Override
    public BigDecimal getAdditionalPrice() {
        return BigDecimal.ZERO;
    }

    @Override
    public String getName() {
        return AppConstant.COLOR_BLUE;
    }
}
