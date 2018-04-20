package io.rama.fueleconomy;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class NhtsaVinDecodingService implements VinDecodingService {

    private final RestTemplate restTemplate;
    private final String nhtsaUrl = "https://vpic.nhtsa.dot.gov/api/vehicles/decodevin/{vin}?format=json";

    public NhtsaVinDecodingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Vehicle findByVin(String vin) {
        Map<String,String> params = new HashMap<>();
        params.put("vin",vin);
        NhtsaResponse response = restTemplate.getForObject(nhtsaUrl,NhtsaResponse.class,params);
        return Vehicle.builder()
                .year(getYearFromResponse(response))
                .make(getMakeFromResponse(response))
                .model(getModelFromResponse(response))
                .build();
    }

    private int getYearFromResponse(NhtsaResponse response) {
        return response.getResults().stream().filter(r -> r.getVariable().equalsIgnoreCase("model year")).map(r -> Integer.parseInt(r.getValue())).findFirst().orElse(0);
    }

    private String getMakeFromResponse(NhtsaResponse response) {
        return response.getResults().stream().filter(r -> r.getVariable().equalsIgnoreCase("make")).map(NhtsaResult::getValue).findFirst().orElse(null);
    }

    private String getModelFromResponse(NhtsaResponse response) {
        return response.getResults().stream().filter(r -> r.getVariable().equalsIgnoreCase("model")).map(NhtsaResult::getValue).findFirst().orElse(null);
    }
}
