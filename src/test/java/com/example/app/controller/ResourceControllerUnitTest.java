package com.example.app.controller;

import com.example.app.data.entity.Resource;
import com.example.app.service.impl.ResourceServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("ResourceController_UnitTest")
@WebMvcTest(controllers = {ResourceController.class})
class ResourceControllerUnitTest {
    @MockBean
    private ResourceServiceImpl resourceService;
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("Should return 201 CREATED")
    @Test
    void createNew() throws Exception {
        final var returnedObject = new Resource(1000l, "Resource");
        Mockito
                .when(resourceService.addResource(Mockito.any(Resource.class)))
                .thenReturn(returnedObject);

        final Resource requestModel = new Resource();
        requestModel.setValue("Resource");

        final MockHttpServletRequestBuilder mockHttpServletRequestBuilder =
                MockMvcRequestBuilders
                        .post("/resources")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestModel));

        final MockHttpServletResponse response =
                mockMvc
                        .perform(mockHttpServletRequestBuilder)
                        .andReturn()
                        .getResponse();

        final var returnedResponseObject
                = objectMapper.readValue(response.getContentAsString(), Resource.class);

        assertAll(
                () -> assertEquals(201, response.getStatus()),
                () -> assertEquals("/resources/1000", response.getHeader("Location")),
                () -> assertEquals(1000l, returnedResponseObject.getId())

        );

    }

    @Test
    void getResourceById() {
    }
}