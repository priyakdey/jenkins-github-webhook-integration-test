package com.example.app.controller;

import com.example.app.data.entity.Resource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("ResourceController_IntegrationTest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ResourceControllerIntegrationTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @DisplayName("Should return 201 CREATED")
    @Test
    void createNew() {
        final String url = "http://localhost:" + port + "/resources";

        final var requestModel = new Resource();
        requestModel.setValue("Resource");

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
        httpHeaders.add(HttpHeaders.ACCEPT, "application/json");

        final HttpEntity<Resource> requestEntity = new HttpEntity<>(requestModel, httpHeaders);

        final ResponseEntity<Resource> responseEntity =
                restTemplate.exchange(url, HttpMethod.POST, requestEntity, Resource.class);

        assertAll(
                () -> assertEquals(201, responseEntity.getStatusCodeValue()),
                () -> assertEquals(URI.create("/resources/1"), responseEntity.getHeaders().getLocation()),
                () -> assertEquals(1l, responseEntity.getBody().getId())
        );
    }
}