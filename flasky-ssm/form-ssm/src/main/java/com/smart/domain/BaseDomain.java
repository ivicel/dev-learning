package com.smart.domain;


import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class BaseDomain implements Serializable {

    private static final long serialVersionUID = -2093210968300338549L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
