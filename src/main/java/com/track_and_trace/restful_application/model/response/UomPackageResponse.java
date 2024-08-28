package com.track_and_trace.restful_application.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UomPackageResponse {

    private int idUomPackage;

    private String uomPackageName;

}
