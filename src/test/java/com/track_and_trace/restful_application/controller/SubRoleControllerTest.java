package com.track_and_trace.restful_application.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.track_and_trace.restful_application.model.request.CreateSubRoleRequest;
import com.track_and_trace.restful_application.model.response.SubRoleResponse;
import com.track_and_trace.restful_application.model.request.UpdateSubRoleRequest;
import com.track_and_trace.restful_application.model.WebResponse;
import com.track_and_trace.restful_application.repository.RoleRepository;
import com.track_and_trace.restful_application.repository.SubRoleRepository;
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
class SubRoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SubRoleRepository subRoleRepository;

    @Test
    void createSubRoleBadRequest() throws Exception{
        CreateSubRoleRequest createSubRoleRequest = new CreateSubRoleRequest();
        createSubRoleRequest.setSubRoleName("");

        mockMvc.perform(
                post("/api/roles/1/subroles")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSubRoleRequest))
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<SubRoleResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            log.info(response.toString());
            assertNotNull(response.getErrors());
        });
    }

    @Test
    void createSubRoleSuccess() throws Exception{
        CreateSubRoleRequest createSubRoleRequest = new CreateSubRoleRequest();
        createSubRoleRequest.setSubRoleName("Super Admin");

        mockMvc.perform(
                post("/api/roles/1/subroles")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSubRoleRequest))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<SubRoleResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            log.info(response.toString());
            assertNull(response.getErrors());
            assertEquals(createSubRoleRequest.getSubRoleName(), response.getData().getSubRoleName());
        });
    }

    @Test
    void findAllSubRoleSuccess() throws Exception{
        mockMvc.perform(
                get("/api/roles/1/subroles")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<List<SubRoleResponse>> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            log.info(response.toString());
            assertNull(response.getErrors());
        });
    }

    @Test
    void findSubRoleSuccess() throws Exception{
        mockMvc.perform(
                get("/api/roles/1/subroles/2")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<SubRoleResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            log.info(response.toString());
            assertNull(response.getErrors());
        });
    }

    @Test
    void deleteSubRoleSuccess() throws Exception{
        mockMvc.perform(
                delete("/api/roles/1/subroles/2")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            log.info(response.toString());
            assertNull(response.getErrors());
        });
    }

    @Test
    void updateSubRoleSuccess() throws Exception{
        UpdateSubRoleRequest updateSubRoleRequest = new UpdateSubRoleRequest();
        updateSubRoleRequest.setSubRoleName("Testing");

        mockMvc.perform(
                put("/api/roles/1/subroles/2")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateSubRoleRequest))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<SubRoleResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            log.info(response.toString());
            assertNull(response.getErrors());
            assertEquals(updateSubRoleRequest.getSubRoleName(), response.getData().getSubRoleName());
        });
    }
}