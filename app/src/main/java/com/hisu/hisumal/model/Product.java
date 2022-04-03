package com.hisu.hisumal.model;

import java.text.DecimalFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int id;
    //Todo: change datatype to String to get img url from internet later
    private int imageResource;
    private String productName;
    private String description;
    private String brand;
    private String category;
    private double price;
    private double discount;
    private boolean isFreeShipping;

    public String getPriceFormat() {
        return "đ " + new DecimalFormat("#,###").format(getPrice());
    }
}