package io.rama.fueleconomy;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/{vin}")
    @ResponseStatus(HttpStatus.OK)
    public VehicleResponse vehicle(@PathVariable String vin) {
        Vehicle vehicle = vehicleService.findByVin(vin);
        return VehicleResponse.builder().vehicle(vehicle).build();
    }
}
