package com.track_and_trace.restful_application;

import com.track_and_trace.restful_application.component.DatabaseConnectionTester;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DatabaseConnectionTesterTest {

    @Autowired
    DatabaseConnectionTester tester;

    @Test
    void testConnection(){
        boolean connected = tester.testConnection();

        if (connected) {
            System.out.println("Database connection successful!");
        } else {
            System.out.println("Database connection failed!");
        }
    }

}