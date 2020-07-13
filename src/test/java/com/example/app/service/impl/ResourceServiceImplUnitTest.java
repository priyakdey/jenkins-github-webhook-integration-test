package com.example.app.service.impl;

import com.example.app.data.entity.Resource;
import com.example.app.data.repository.ResourceRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("ResourceServiceImpl_Unit_Test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ResourceServiceImplUnitTest {
    @Mock
    private ResourceRepository resourceRepository;
    @InjectMocks
    private ResourceServiceImpl resourceService;


    @DisplayName("Should return the saved Resource")
    @Test
    void addResourceTest() {
        final Resource mockResource = new Resource(1000l, "Resource 1");
        Mockito.when(resourceRepository.save(Mockito.any(Resource.class)))
                .thenReturn(mockResource);

        final Resource toBeSavedResource = new Resource();
        toBeSavedResource.setValue("Resource 1");

        final Resource savedResource = resourceService.addResource(toBeSavedResource);

        assertAll(
                () -> assertNotNull(savedResource.getId())
        );
    }

    @DisplayName("Should return the Resource by id")
    @Test
    void getResourceById() {
        final Resource mockResource = new Resource(1000l, "Resource 1");
        Mockito.when(resourceRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(mockResource));

        final Resource fetchedResource = resourceService.getResourceById(1000l);

        assertAll(
                () -> assertNotNull(fetchedResource)
        );
    }
}