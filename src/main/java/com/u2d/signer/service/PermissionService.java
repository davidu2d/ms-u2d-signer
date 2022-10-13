package com.u2d.signer.service;

import com.u2d.signer.dto.request.PermissionRequest;
import com.u2d.signer.dto.response.PermissionResponse;
import com.u2d.signer.entity.Permission;

import java.util.Optional;

public interface PermissionService {

    PermissionResponse create(PermissionRequest permissionRequest);

    Optional<Permission> findByDescription(String description);
}
