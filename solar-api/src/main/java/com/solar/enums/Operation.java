package com.solar.enums;

import java.util.function.DoubleBinaryOperator;

/**
 * @author hushaoge
 * @date 2021/6/18
 */
public enum Operation {

    PLUS("+", (x, y) -> x + y );
    private final String symbol;

    private DoubleBinaryOperator dbo;

    Operation(String symbol, DoubleBinaryOperator dbo){
        this.symbol = symbol;
        this.dbo = dbo;
    }

    public double apply(double x, double y) {
        return dbo.applyAsDouble(x, y);
    }
}
