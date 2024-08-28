package com.track_and_trace.restful_application.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateMenuRequest {

    @JsonIgnore
    @Positive
    private int idMenu;

    @NotBlank
    @Size(max = 255)
    private String menuName;

    @NotBlank
    @Size(max = 255)
    private String labelMenu;

}
