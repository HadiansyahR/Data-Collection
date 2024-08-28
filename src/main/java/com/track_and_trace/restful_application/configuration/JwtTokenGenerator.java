package com.track_and_trace.restful_application.configuration;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.track_and_trace.restful_application.entity.Account;
import com.track_and_trace.restful_application.model.response.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;
import java.util.Date;

@Configuration
public class JwtTokenGenerator {

    public static class ClaimSet{
        public static final String ROLE_CLAIM = "role";
    }

    @Autowired
    private JWK jwk;

    @Autowired
    private RSAKey rsaPrivateKey;

    public TokenResponse generateToken(Account account){
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.add(Calendar.DATE, 1);

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(account.getUsername())
                .issuer("enhaa")
                .issueTime(new Date())
                .expirationTime(expirationDate.getTime())
                .claim(ClaimSet.ROLE_CLAIM, account.getSubRole().getSubRoleName())
                .build();

        SignedJWT signedJWT = new SignedJWT(new JWSHeader.Builder(JWSAlgorithm.RS256).jwk(jwk).build(), claimsSet);

        try {
            JWSSigner signer = new RSASSASigner(rsaPrivateKey);
            signedJWT.sign(signer);
        } catch (JOSEException e){
            e.printStackTrace();
        }

        String token = signedJWT.serialize();

//        System.out.println("Token Response: "+new TokenResponse(token, expirationDate.getTimeInMillis()));
        return new TokenResponse(token, expirationDate.getTimeInMillis(), account.getSubRole().getIdSubRole());
    }
}
