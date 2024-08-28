package com.track_and_trace.restful_application.service;

import com.track_and_trace.restful_application.model.response.TokenResponse;
import com.track_and_trace.restful_application.model.request.UsernamePasswordRequest;

public interface TokenService {
    TokenResponse createToken(UsernamePasswordRequest usernamePasswordRequest);
}
