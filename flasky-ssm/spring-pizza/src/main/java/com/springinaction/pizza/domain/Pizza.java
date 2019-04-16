package com.springinaction.pizza.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pizza implements Serializable {

    private static final long serialVersionUID = 5726112052387589258L;
    private PizzaSize size;
    private List<Topping> toppings;

    public Pizza() {
        this.toppings = new ArrayList<>();
        size = PizzaSize.LARGE;
    }

    public PizzaSize getSize() {
        return size;
    }

    public void setSize(PizzaSize size) {
        this.size = size;
    }

    public void setSize(String size) {
        this.size = PizzaSize.valueOf(size);
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    public void setTopping(String[] toppingStrings) {
        for (String t : toppingStrings) {
            toppings.add(Topping.valueOf(t));
        }
    }
}
