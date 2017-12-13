package com.javen.sizuka.model;

import lombok.Data;

import java.util.List;

/**
 * Created by javen on 2017/12/4.
 */
@Data
public class Page {
    private Integer page;
    private Integer pageSize = 5;
    private Integer pages;
    private Boolean lastPage;
    private List<Object> data;
}
