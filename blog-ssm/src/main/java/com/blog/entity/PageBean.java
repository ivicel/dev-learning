package com.blog.entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageBean {
    private int page;
    private int pageSize;
    private int start;

    public PageBean(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }
}
