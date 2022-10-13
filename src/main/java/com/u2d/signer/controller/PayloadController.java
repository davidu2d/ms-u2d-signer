package com.u2d.signer.controller;

import com.u2d.signer.dto.request.PayloadRequest;
import com.u2d.signer.dto.response.PayloadResponse;
import com.u2d.signer.service.PayloadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("payload")
@Tag(name = "Payload", description = "API para geração de payload Pix")
public class PayloadController {

    public PayloadService service;

    @Autowired
    public PayloadController(PayloadService service) {
        this.service = service;
    }


    @PostMapping
    @Operation(summary = "Endpoint para gerar payload pix copia e cola")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = PayloadResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content)
    })
    public ResponseEntity<PayloadResponse> createPayload(@RequestBody PayloadRequest payloadRequest) {
        PayloadResponse responseDto = service.createPayload(payloadRequest);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
