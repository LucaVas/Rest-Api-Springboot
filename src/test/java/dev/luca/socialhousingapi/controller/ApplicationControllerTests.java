package dev.luca.socialhousingapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.luca.socialhousingapi.model.SocialHousingApplication;
import dev.luca.socialhousingapi.service.SocialHousingApplicationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ApplicationController.class)
public class ApplicationControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    SocialHousingApplicationService service;

    @Autowired
    ObjectMapper mapper;


    @Test
    void should_find_all_applications() throws Exception {
        // Given
        Timestamp timestamp = Timestamp.from(Instant.now());
        List<SocialHousingApplication> applications = List.of(new SocialHousingApplication("100001", "New", timestamp, timestamp));
        Mockito.when(service.getApplications()).thenReturn(applications);

        // When/Then
        mockMvc.perform(get("/api/v1/applications"))
                .andExpectAll(
                        status().isOk(),
                        content().json(mapper.writeValueAsString(applications))
                );
    }
}
