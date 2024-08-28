package com.track_and_trace.restful_application.service.implement;

import com.track_and_trace.restful_application.entity.Account;
import com.track_and_trace.restful_application.entity.SubRole;
import com.track_and_trace.restful_application.model.response.AccountResponse;
import com.track_and_trace.restful_application.model.request.CreateAccountRequest;
import com.track_and_trace.restful_application.repository.AccountRepository;
import com.track_and_trace.restful_application.repository.SubRoleRepository;
import com.track_and_trace.restful_application.security.BCrypt;
import com.track_and_trace.restful_application.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private SubRoleRepository subRoleRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ValidationService validationService;

    @Override
    public AccountResponse responseBuilder(Account entity) {
        return AccountResponse
                .builder()
                .username(entity.getUsername())
                .nameAccount(entity.getNameAccount())
                .email(entity.getEmail())
                .idSubRole(entity.getSubRole().getIdSubRole())
                .status(entity.getStatus())
                .build();
    }

    @Transactional
    @Override
    public AccountResponse createAccount(CreateAccountRequest createAccountRequest) {
        validationService.validate(createAccountRequest);

        if (accountRepository.existsById(createAccountRequest.getUsername())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }

        SubRole subRole = subRoleRepository.findById(createAccountRequest.getIdSubRole())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sub Role is not found"));

        System.out.println(createAccountRequest.getStatus());

        Account account = new Account();
        account.setUsername(createAccountRequest.getUsername());
        account.setPassword(BCrypt.hashpw(createAccountRequest.getPassword(), BCrypt.gensalt()));
        account.setNameAccount(createAccountRequest.getNameAccount());
        account.setEmail(createAccountRequest.getEmail());
        account.setSubRole(subRole);
        account.setStatus(createAccountRequest.getStatus());
        accountRepository.save(account);

        return responseBuilder(account);
    }

    @Override
    public Account getAccountByUsername(String username) {

        return accountRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account is not found"));
    }

    @Override
    public AccountResponse getAccountResponseByUsername(String username) {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account is not found"));

        return responseBuilder(account);
    }

    @Override
    public Boolean checkAccountPassword(String username, String password, int flag) {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account is not found"));

        log.info("Account object password: {}", account.getPassword());
        log.info("Argument password: {}", password);

        if (flag == account.getSubRole().getRole().getIdRole()){
            if (BCrypt.checkpw(password, account.getPassword()))
                return true;
            else throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Wrong username or password");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account is not found");
        }
    }
}
