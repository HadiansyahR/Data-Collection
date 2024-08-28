package com.track_and_trace.restful_application.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateMenuPermissionRequest {

    @JsonIgnore
    private Integer idMenuPermission;

    @JsonIgnore
    @Positive
    private int idMenu;

    @JsonIgnore
    @Positive
    private int idSubRole;

    @Min(value = 0)
    @Max(value = 1)
    private Integer createPermission;

    @Min(value = 0)
    @Max(value = 1)
    private Integer updatePermission;

    @Min(value = 0)
    @Max(value = 1)
    private Integer deletePermission;

    @Min(value = 0)
    @Max(value = 1)
    private Integer viewPermission;
}
