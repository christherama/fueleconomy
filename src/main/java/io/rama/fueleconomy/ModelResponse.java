package io.rama.fueleconomy;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ModelResponse {
    private List<Model> models;
}
