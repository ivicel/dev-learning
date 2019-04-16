package com.springinaction.pizza.domain;

import java.io.Serializable;

public class Payment implements Serializable {
    private static final long serialVersionUID = 3714989243694908697L;

    private float amount;

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
