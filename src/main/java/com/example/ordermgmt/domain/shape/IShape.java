package com.example.ordermgmt.domain.shape;

import java.math.BigDecimal;

/**
 * Abstract for shape category
 */
public interface IShape {

    /**
     * Get the unit price of shape category
     * @return BigDecimal
     */
    BigDecimal getBasicPrice();

    /**
     * Get name
     * @return String
     */
    String getName();
}
