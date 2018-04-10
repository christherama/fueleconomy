package io.rama.fueleconomy;

import lombok.Data;

@Data
public class Vehicle {
    private String vin;
    private int year;
    private String make;
    private String model;
}
