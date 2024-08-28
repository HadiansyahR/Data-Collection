package com.track_and_trace.restful_application.service;

import com.track_and_trace.restful_application.entity.Account;
import com.track_and_trace.restful_application.model.response.AccountResponse;
import com.track_and_trace.restful_application.model.request.CreateAccountRequest;

public interface AccountService extends CrudService<Account, AccountResponse>{
    AccountResponse createAccount(CreateAccountRequest createAccountRequest);
    Account getAccountByUsername(String username);
    AccountResponse getAccountResponseByUsername(String username);
    Boolean checkAccountPassword(String username, String password, int flag);
}
