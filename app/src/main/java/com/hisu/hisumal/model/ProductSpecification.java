package com.hisu.hisumal.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductSpecification implements Serializable {
    private int productID;
    private String cpu;
    private String os;
    private String ram;
    private String gpu;
    private String monitor;
    private String hardDrive;
    private String connectionGate;
    private String keyboard;
    private String battery;
    private float weight;
}