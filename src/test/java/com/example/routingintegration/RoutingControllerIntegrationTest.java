package com.example.routingintegration;

import com.example.routing.RoutingApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = RoutingApplication.class)
@AutoConfigureMockMvc
public class RoutingControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @CsvFileSource(resources = "/test-data.csv", delimiter = ';')
    public void testRoutingEndpoint(String origin, String destination, int responseCode, String expectedResponse) throws Exception {
        mockMvc.perform(get("/routing/{origin}/{destination}"
                        .replace("{origin}", origin)
                        .replace("{destination}", destination))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(responseCode))
                .andExpect(content().string(expectedResponse));
    }
}