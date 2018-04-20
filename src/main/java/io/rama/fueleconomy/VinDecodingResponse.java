package io.rama.fueleconomy;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VinDecodingResponse {
    private Vehicle vehicle;
}
