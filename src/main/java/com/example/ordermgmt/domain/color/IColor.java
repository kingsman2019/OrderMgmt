package com.example.ordermgmt.domain.color;

import java.math.BigDecimal;

/**
 * Abstract for color category
 */
public interface IColor {
    /**
     * Get additional charge price for color category
     * @return BigDecimal
     */
    BigDecimal getAdditionalPrice();

    /**
     * Color name
     * @return String
     */
    String getName();
}
