package com.example.app.service;

import com.example.app.data.entity.Resource;

public interface ResourceService {
    Resource addResource(Resource resource);

    Resource getResourceById(Long id);
}
