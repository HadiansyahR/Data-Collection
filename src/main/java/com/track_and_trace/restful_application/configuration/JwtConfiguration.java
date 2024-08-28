package com.track_and_trace.restful_application.configuration;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Arrays;

@Configuration
public class JwtConfiguration {

    @Value("${server.ssl.key-store}")
    private String keyStorePath;

    @Value("${server.ssl.key-store-password}")
    private String keyStorePass;

    @Value("classpath:selfsigned.jks")
    private Resource keyStoreResource;

    @Bean
    public KeyStore keyStore(){
        KeyStore keyStore;

        try {
            keyStore = KeyStore.getInstance("JKS");
            char[] password = keyStorePass.toCharArray();
//            keyStore.load(new FileInputStream(keyStorePath), password);
//            keyStore.load(keyStoreResource.getInputStream(), password);

            try (FileInputStream fileInputStream = new FileInputStream(keyStorePath)) {
                keyStore.load(fileInputStream, password);
            }
//            System.out.println("Keystore: " + keyStore);
            return keyStore;
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e){
            e.printStackTrace();
        }

        return null;
    }

    @Bean
    public JWK jwk(){
        try {
//            System.out.println("JWK load Keystore: "+JWK.load(keyStore(), "enhaa", keyStorePass.toCharArray()));

            return JWK.load(keyStore(), "enhaa", keyStorePass.toCharArray());
        } catch (KeyStoreException | JOSEException e){
            e.printStackTrace();
        }

        return null;
    }

    @Bean
    public RSAKey rsaPrivateKey(){
        RSAKey rsaJWK = null;

        try {
            rsaJWK = RSAKey.load(keyStore(), "enhaa", keyStorePass.toCharArray());
        } catch (KeyStoreException | JOSEException e){
            e.printStackTrace();
        }

//        System.out.println("RSA JWK: "+rsaJWK);
        return rsaJWK;
    }

    @Bean
    public JWKSet jwkSet(){
//        System.out.println("JWK Set: " + new JWKSet(Arrays.asList(jwk())));
        return new JWKSet(Arrays.asList(jwk()));
    }
}
