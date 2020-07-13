package com.example.app.service.impl;

import com.example.app.data.entity.Resource;
import com.example.app.service.ResourceService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ResourceServiceImpl_Integration_Test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ResourceServiceImplIntegrationTest {
    @Autowired
    private ResourceService resourceService;

    @DisplayName("Should return the saved Resource")
    @Test
    void addResource() {
        final Resource toBeSaved = new Resource();
        toBeSaved.setValue("Resource");

        final Resource actual = resourceService.addResource(toBeSaved);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(1, actual.getId())
        );
    }

    @DisplayName("Should return the Resource by id")
    @Test
    void getResourceById() {
        final Resource toBeSaved = new Resource();
        toBeSaved.setValue("Resource");

        resourceService.addResource(toBeSaved);

        final var actual = resourceService.getResourceById(1l);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals("Resource", actual.getValue())
        );
    }

}