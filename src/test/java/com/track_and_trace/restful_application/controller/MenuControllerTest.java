package com.track_and_trace.restful_application.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.track_and_trace.restful_application.model.request.CreateMenuRequest;
import com.track_and_trace.restful_application.model.response.MenuResponse;
import com.track_and_trace.restful_application.model.request.UpdateMenuRequest;
import com.track_and_trace.restful_application.model.WebResponse;
import com.track_and_trace.restful_application.repository.MenuRepository;
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
class MenuControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MenuRepository menuRepository;

    @Test
    void createMenuBadRequest() throws Exception{
        CreateMenuRequest request = new CreateMenuRequest();
        request.setMenuName("");
        request.setLabelMenu("");

        mockMvc.perform(
        post("/api/menus")
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
    void createMenuSuccess() throws Exception{
        CreateMenuRequest request = new CreateMenuRequest();

        request.setMenuName("Inbox Mobile");
        request.setLabelMenu("Inbox");
        mockMvc.perform(
                post("/api/menus")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isOk()
        ).andDo( result ->{
            WebResponse<MenuResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            log.info(response.getData().toString());
            assertNull(response.getErrors());
            assertEquals("Inbox Mobile", response.getData().getMenuName());
            assertEquals("Inbox", response.getData().getLabelMenu());
        });
    }

    @Test
    void findByIdSuccess() throws Exception {
        mockMvc.perform(
                get("/api/menus/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<MenuResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            log.info(response.toString());
            assertNull(response.getErrors());
        });
    }

    @Test
    void findAllMenuSuccess() throws Exception{
        mockMvc.perform(
                get("/api/menus")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
            status().isOk()
        ).andDo(result -> {
            WebResponse<List<MenuResponse>> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            log.info(response.toString());
            assertNull(response.getErrors());
        });
    }

    @Test
    void findAllMenuUnauthorized() throws Exception{
        mockMvc.perform(
                get("/api/menus")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isUnauthorized()
        ).andDo(result -> {
            WebResponse<List<MenuResponse>> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            log.info(response.toString());
            assertNotNull(response.getErrors());
        });
    }

    @Test
    void updateMenuBadRequest() throws Exception{
        UpdateMenuRequest request = new UpdateMenuRequest();
        request.setMenuName("");
        request.setLabelMenu("");

        mockMvc.perform(
                put("/api/menus/1")
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
    void updateMenuSuccess() throws Exception{
        CreateMenuRequest request = new CreateMenuRequest();

        request.setMenuName("Dashboard Mobile");
        request.setLabelMenu("Dashboard");

        mockMvc.perform(
                put("/api/menus/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isOk()
        ).andDo( result ->{
            WebResponse<MenuResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            log.info(response.getData().toString());
            assertNull(response.getErrors());
            assertEquals("Dashboard Mobile", response.getData().getMenuName());
            assertEquals("Dashboard", response.getData().getLabelMenu());
        });
    }

    @Test
    void deleteSuccess() throws Exception {
        mockMvc.perform(
                delete("/api/menus/0")
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