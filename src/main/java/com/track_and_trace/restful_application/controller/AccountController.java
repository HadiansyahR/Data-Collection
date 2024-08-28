package com.track_and_trace.restful_application.controller;

import com.track_and_trace.restful_application.model.response.AccountResponse;
import com.track_and_trace.restful_application.model.request.CreateAccountRequest;
import com.track_and_trace.restful_application.model.WebResponse;
import com.track_and_trace.restful_application.service.implement.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = {"http://localhost", "http://localhost:8080", "http://127.0.0.1:8000"})
@RequestMapping("/")
public class AccountController {

    @Autowired
    AccountServiceImpl accountService;

    @GetMapping(
            path = "accounts/{username}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<AccountResponse> getAccountByUsername(@PathVariable("username") String username){
        AccountResponse accountResponse = accountService.getAccountResponseByUsername(username);

        return WebResponse.<AccountResponse>builder().data(accountResponse).size(1).build();
    }

    @PostMapping(
            path = "accounts",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<AccountResponse> create(@RequestBody CreateAccountRequest request){
        AccountResponse accountResponse = accountService.createAccount(request);

        return WebResponse.<AccountResponse>builder().data(accountResponse).size(1).build();
    }
}
