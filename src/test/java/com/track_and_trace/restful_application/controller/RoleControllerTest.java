package com.track_and_trace.restful_application.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.track_and_trace.restful_application.model.request.CreateRoleRequest;
import com.track_and_trace.restful_application.model.response.RoleResponse;
import com.track_and_trace.restful_application.model.request.UpdateRoleRequest;
import com.track_and_trace.restful_application.model.WebResponse;
import com.track_and_trace.restful_application.repository.RoleRepository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class RoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void createRoleBadRequest() throws Exception{
        CreateRoleRequest request = new CreateRoleRequest();
        request.setRoleName("");
        request.setRoleStatus("");

        mockMvc.perform(
                post("/api/roles")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isBadRequest()
        ).andDo( result ->{
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {
            });

            log.info(response.getErrors());
            assertNotNull(response.getErrors());
        });
    }

    @Test
    void createRoleSuccess() throws Exception{
        CreateRoleRequest request = new CreateRoleRequest();
        request.setRoleName("Mobile User");
        request.setRoleStatus("test");

        mockMvc.perform(
                post("/api/roles")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isOk()
        ).andDo( result ->{
            WebResponse<RoleResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            log.info(response.getData().toString());
            assertNull(response.getErrors());
            assertEquals("Mobile User", response.getData().getRoleName());
            assertEquals("test", response.getData().getRoleStatus());
        });
    }

    @Test
    void findByIdSuccess() throws Exception {
        mockMvc.perform(
                get("/api/roles/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<RoleResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            log.info(response.toString());
            assertNull(response.getErrors());
        });
    }

    @Test
    void findAllSuccess() throws Exception {
        mockMvc.perform(
                get("/api/roles")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<List<RoleResponse>> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            log.info(response.toString());
            assertNull(response.getErrors());
        });
    }

    @Test
    void updateRoleSuccess() throws Exception{
        UpdateRoleRequest request = new UpdateRoleRequest();
        request.setRoleName("Testing");
        request.setRoleStatus("test");

        mockMvc.perform(
                put("/api/roles/2")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isOk()
        ).andDo( result ->{
            WebResponse<RoleResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            log.info(response.getData().toString());
            assertNull(response.getErrors());
            assertEquals("Testing", response.getData().getRoleName());
            assertEquals("test", response.getData().getRoleStatus());
        });
    }

    @Test
    void deleteSuccess() throws Exception {
        mockMvc.perform(
                delete("/api/roles/3")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            log.info(response.getData());
            assertNull(response.getErrors());
        });
    }
}