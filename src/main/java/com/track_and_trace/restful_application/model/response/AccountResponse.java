package com.track_and_trace.restful_application.model.response;

import com.track_and_trace.restful_application.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountResponse {

    private String username;

    private String nameAccount;

    private String email;

    private int idSubRole;

    private Status status;

}
