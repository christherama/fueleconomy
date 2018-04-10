package io.rama.fueleconomy;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VehicleResponse {
    private Vehicle vehicle;
}
