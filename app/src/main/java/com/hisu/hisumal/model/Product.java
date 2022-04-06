package com.hisu.hisumal.model;

import java.io.Serializable;
import java.text.DecimalFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    private int id;
    private int imageResource;
    private String productName;
    private String description;
    private String brand;
    private double price;
    private double discount;
    private boolean isFreeShipping;
    private double rate;
    private int quantityInStock;
    private ProductSpecification specification;

    public String getPriceFormat() {
        return "đ " + new DecimalFormat("#,###").format(getPrice());
    }

    public String getDiscountFormat() {
        double discountPrice = getPrice() + (getPrice() * (getDiscount() / 100));
        return "đ " + new DecimalFormat("#,###").format(discountPrice);
    }
}