package io.rama.fueleconomy;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    private final VinDecodingService vinDecodingService;

    public VehicleController(VinDecodingService vinDecodingService) {
        this.vinDecodingService = vinDecodingService;
    }

    @GetMapping("/{vin}")
    @ResponseStatus(HttpStatus.OK)
    public VinDecodingResponse decodeVin(@PathVariable String vin) {
        Vehicle vehicle = vinDecodingService.findByVin(vin);
        return VinDecodingResponse.builder().vehicle(vehicle).build();
    }

    @GetMapping("/{vin}/mpg")
    @ResponseStatus(HttpStatus.OK)
    public ModelResponse getModels() {
        return ModelResponse.builder().models(new ArrayList<>()).build();
    }
}
