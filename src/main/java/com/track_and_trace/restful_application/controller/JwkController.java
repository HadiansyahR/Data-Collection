package com.track_and_trace.restful_application.controller;

import com.nimbusds.jose.jwk.JWKSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/.well-known/jwks.json")
public class JwkController {

    @Autowired
    private JWKSet jwkSet;

    @GetMapping
    public Object keys(){
        return jwkSet.toJSONObject();
    }
}
