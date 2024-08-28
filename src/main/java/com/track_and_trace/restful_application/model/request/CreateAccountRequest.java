package com.track_and_trace.restful_application.model.request;

import com.track_and_trace.restful_application.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAccountRequest {

    @NotBlank
    @Size(max = 50)
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    @Size(max = 100)
    private String nameAccount;

    @NotBlank
    @Email
    @Size(max = 300)
    private String email;

    @Positive
    @NotNull
    private int idSubRole;

    @NotNull
    private Status status;

}
