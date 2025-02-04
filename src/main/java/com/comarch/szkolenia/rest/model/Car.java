package com.comarch.szkolenia.rest.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Car {
    private String brand;
    private String model;
    private double price;
    private int year;
    private String plate;
    private boolean rent;
}
