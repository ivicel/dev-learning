package com.springinaction.pizza.service;

import com.springinaction.pizza.domain.Order;
import com.springinaction.pizza.domain.Pizza;
import com.springinaction.pizza.domain.PizzaSize;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

public class PricingEngineImpl implements PricingEngine {

    private static final Logger LOGGER = Logger.getLogger(PricingEngineImpl.class);
    private static Map<PizzaSize, Float> SIZE_PRICES;

    static {
        SIZE_PRICES = new HashMap<>();
        SIZE_PRICES.put(PizzaSize.SMALL, 6.99f);
        SIZE_PRICES.put(PizzaSize.MEDIUM, 7.99f);
        SIZE_PRICES.put(PizzaSize.LARGE, 8.99f);
        SIZE_PRICES.put(PizzaSize.GINORMOUS, 9.99f);
    }

    private static float PRICE_PER_TOPPING = 0.20f;

    @Override
    public float calculateOrderTotal(Order order) {
        LOGGER.debug("Calculating order total");

        float total = 0.0f;

        for (Pizza pizza : order.getPizzas()) {
            float pizzaPrice = SIZE_PRICES.get(pizza.getSize());
            if (pizza.getToppings().size() > 2) {
                pizzaPrice += (pizza.getToppings().size() * PRICE_PER_TOPPING);
            }
            total += pizzaPrice;
        }

        return total;
    }
}
