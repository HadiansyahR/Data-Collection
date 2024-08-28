package com.track_and_trace.restful_application.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateSubRoleRequest {

    @JsonIgnore
    @NotNull
    @Positive
    private int idSubRole;

    @JsonIgnore
    @NotNull
    @Positive
    private int idRole;

    @NotBlank
    @Size(max = 255)
    private String subRoleName;
}
