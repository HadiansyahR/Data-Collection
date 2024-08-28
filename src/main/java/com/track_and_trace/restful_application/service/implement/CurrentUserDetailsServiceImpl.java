package com.track_and_trace.restful_application.service.implement;

import com.track_and_trace.restful_application.entity.Account;
import com.track_and_trace.restful_application.entity.CurrentPrincipal;
import com.track_and_trace.restful_application.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = accountService.getAccountByUsername(username);

        if (user == null)
            throw new UsernameNotFoundException("Username "+username+" not found");

        return new CurrentPrincipal(user);
    }
}
