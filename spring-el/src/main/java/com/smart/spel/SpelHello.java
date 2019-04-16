package com.smart.spel;

import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class SpelHello {
    public static void main(String[] args) {
        SpelExpressionParser parser = new SpelExpressionParser();
        Expression expr = parser.parseExpression("'Hello' + ' World'");
        String message = expr.getValue(String.class);
        System.out.println(message);
    }
}
