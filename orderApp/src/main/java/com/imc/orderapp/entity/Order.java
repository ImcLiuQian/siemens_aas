package com.imc.orderapp.entity;

import lombok.Data;

@Data
public class Order {
    //盖子颜色
    private Integer lidType;
    //底座颜色
    private Integer baseType;
    //生产数量（小一点）
    private Integer capacity;
}
