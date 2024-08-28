package com.track_and_trace.restful_application.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMenuPermissionRequest {

    @JsonIgnore
    @NotNull
    @Positive
    private int idMenu;

    @JsonIgnore
    @NotNull
    @Positive
    private int idSubRole;

    @NotNull
    @Min(value = 0)
    @Max(value = 1)
    private int createPermission;

    @NotNull
    @Min(value = 0)
    @Max(value = 1)
    private int updatePermission;

    @NotNull
    @Min(value = 0)
    @Max(value = 1)
    private int deletePermission;

    @NotNull
    @Min(value = 0)
    @Max(value = 1)
    private int viewPermission;
}
