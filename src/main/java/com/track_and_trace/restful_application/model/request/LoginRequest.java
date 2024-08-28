package com.track_and_trace.restful_application.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {

    @NotBlank
    @Size(max = 50)
    private String username;

    @NotBlank
    private String password;

    @NotNull
    @Min(value = 1)
    @Max(value = 3)
    private int flag;
}
