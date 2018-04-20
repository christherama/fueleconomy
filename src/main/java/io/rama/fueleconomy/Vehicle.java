package io.rama.fueleconomy;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Vehicle {
    private String vin;
    private int year;
    private String make;
    private String model;
}
