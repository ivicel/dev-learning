package com.blog.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BlogType implements Serializable {
    private static final long serialVersionUID = 1188807859490356988L;

    private Integer id;
    private String typeName;
    private Integer blogCount;
    private Integer orderNo;
}
