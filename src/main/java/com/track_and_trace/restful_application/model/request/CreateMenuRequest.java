package com.track_and_trace.restful_application.model.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMenuRequest {

    @NotBlank
    @Size(max = 255)
    private String menuName;

    @NotBlank
    @Size(max = 255)
    private String labelMenu;

}