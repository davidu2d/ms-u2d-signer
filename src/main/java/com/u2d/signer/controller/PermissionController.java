package com.u2d.signer.controller;

import com.u2d.signer.dto.request.PermissionRequest;
import com.u2d.signer.dto.response.PermissionResponse;
import com.u2d.signer.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permission")
public class PermissionController {

    private PermissionService service;

    @Autowired
    public PermissionController(PermissionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PermissionResponse> create(@RequestBody PermissionRequest request) {
        PermissionResponse response = this.service.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
