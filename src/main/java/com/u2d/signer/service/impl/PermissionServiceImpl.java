package com.u2d.signer.service.impl;

import com.u2d.signer.dto.request.PermissionRequest;
import com.u2d.signer.dto.response.PermissionResponse;
import com.u2d.signer.entity.Permission;
import com.u2d.signer.exception.ResourceExistsException;
import com.u2d.signer.repository.PermissionRepository;
import com.u2d.signer.service.PermissionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PermissionServiceImpl implements PermissionService {

    private PermissionRepository repository;

    private ModelMapper mapper;

    @Autowired
    public PermissionServiceImpl(PermissionRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public PermissionResponse create(PermissionRequest permissionRequest) {
        Optional<Permission> permission = findByDescription(permissionRequest.getDescription());
        if(permission.isPresent())
            throw new ResourceExistsException(Permission.class);
        Permission permissionSaved = repository.save(mapper.map(permissionRequest, Permission.class));
        return mapper.map(permissionSaved, PermissionResponse.class);
    }

    public Optional<Permission> findByDescription(String description) {
        return this.repository.findByDescription(description);
    }
}
