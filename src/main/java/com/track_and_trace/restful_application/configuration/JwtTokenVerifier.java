package com.track_and_trace.restful_application.configuration;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.BadJOSEException;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTClaimsVerifier;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Component
public class JwtTokenVerifier {

    @Autowired
    private JWKSet jwkSet;
    private Logger log = LoggerFactory.getLogger(JwtTokenVerifier.class);

    public Authentication isVerified(String token){
        ConfigurableJWTProcessor jwtProcessor = new DefaultJWTProcessor<>();
        JWKSource jwkSource = new ImmutableJWKSet<>(jwkSet);

        JWTClaimsSet exactClaimsSet = new JWTClaimsSet.Builder().issuer("enhaa").build();
        HashSet<String> requiredClaim = new HashSet<>(Arrays.asList("sub", "iat", "exp"));
        new DefaultJWTClaimsVerifier<>(exactClaimsSet, requiredClaim);

        JWSAlgorithm jwsAlgorithm = JWSAlgorithm.RS256;
        JWSKeySelector jwsKeySelector = new JWSVerificationKeySelector<>(jwsAlgorithm, jwkSource);
        jwtProcessor.setJWSKeySelector(jwsKeySelector);

        try {
            jwtProcessor.process(token, null);

            SignedJWT signedJWT = SignedJWT.parse(token);
            JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();

            String sub = claimsSet.getSubject();
            Date expiredAt = claimsSet.getExpirationTime();
            String role = claimsSet.getStringClaim(JwtTokenGenerator.ClaimSet.ROLE_CLAIM);

            if (expiredAt.getTime() > new Date().getTime()){
                List<GrantedAuthority> granted = Arrays.asList(new SimpleGrantedAuthority(role));
//                System.out.println("User Principal"+new UsernamePasswordAuthenticationToken(sub, token, granted));
                return new UsernamePasswordAuthenticationToken(sub, token, granted);
            }

            return null;
        } catch (ParseException | BadJOSEException | JOSEException e){
            log.debug("Token verification failed", e);
        }
        return null;
    }
}
