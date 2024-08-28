package com.track_and_trace.restful_application.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUomRequest {

    @JsonIgnore
    @Positive
    private int idUom;

    @NotBlank
    @Size(max = 100)
    private String uomName;
}
