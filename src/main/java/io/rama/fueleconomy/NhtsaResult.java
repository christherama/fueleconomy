package io.rama.fueleconomy;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NhtsaResult {

    @JsonProperty("Value")
    private String value;

    @JsonProperty("ValueId")
    private String valueId;

    @JsonProperty("Variable")
    private String variable;

    @JsonProperty("VariableId")
    private String variableId;
}
