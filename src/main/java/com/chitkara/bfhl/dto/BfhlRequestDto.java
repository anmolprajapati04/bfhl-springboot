package com.chitkara.bfhl.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class BfhlRequestDto {

    @NotNull(message = "data field must not be null")
    @JsonProperty("data")
    private List<String> data;

    public BfhlRequestDto() {
    }

    public BfhlRequestDto(List<String> data) {
        this.data = data;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
