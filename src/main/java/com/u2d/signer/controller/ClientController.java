package com.u2d.signer.controller;

import com.u2d.signer.dto.request.ClientRequest;
import com.u2d.signer.dto.response.ClientResponse;
import com.u2d.signer.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("client")
public class ClientController {

    public ClientService service;

    @Autowired
    public ClientController(ClientService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<ClientResponse> create(@RequestBody ClientRequest clientRequest) {
        ClientResponse responseDto = service.createClient(clientRequest);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
