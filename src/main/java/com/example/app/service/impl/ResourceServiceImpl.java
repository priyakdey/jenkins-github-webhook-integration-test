package com.example.app.service.impl;

import com.example.app.data.entity.Resource;
import com.example.app.data.repository.ResourceRepository;
import com.example.app.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {
    private final ResourceRepository resourceRepository;

    @Autowired
    public ResourceServiceImpl(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @Override
    public Resource addResource(Resource resource) {
        return resourceRepository.save(resource);
    }

    @Override
    @Transactional(readOnly = true)
    public Resource getResourceById(Long id) {
        return resourceRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }
}
