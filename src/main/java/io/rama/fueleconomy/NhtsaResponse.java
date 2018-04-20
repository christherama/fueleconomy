package io.rama.fueleconomy;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class NhtsaResponse {
    @JsonProperty("Results")
    private List<NhtsaResult> results;
}
