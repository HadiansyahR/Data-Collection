package com.track_and_trace.restful_application.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class DatabaseConnectionTester {
    @Autowired
    private EntityManager entityManager;

    public boolean testConnection() {
        try {
            entityManager.createNativeQuery("SELECT 1").getSingleResult();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
