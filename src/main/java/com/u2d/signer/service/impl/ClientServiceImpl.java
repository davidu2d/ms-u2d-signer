package com.u2d.signer.service.impl;

import com.u2d.signer.constants.PayloadConstants;
import com.u2d.signer.dto.request.ClientRequest;
import com.u2d.signer.dto.response.ClientResponse;
import com.u2d.signer.entity.Client;
import com.u2d.signer.exception.ResourceExistsException;
import com.u2d.signer.exception.ResourceNotFoundException;
import com.u2d.signer.repository.ClientRepository;
import com.u2d.signer.service.ClientService;
import com.u2d.signer.utils.PasswordUtil;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    private ClientRepository repository;

    private ModelMapper modelMapper;
    @Autowired
    public ClientServiceImpl(ClientRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public ClientResponse createClient(ClientRequest clientRequest) {
        this.repository.findById(clientRequest.getClientId()).orElseThrow(() -> new ResourceExistsException(Client.class));

        Client client = converterDtoToEntity(clientRequest);
        this.repository.save(client);
        return modelMapper.map(client, ClientResponse.class);
    }

    private Client converterDtoToEntity(ClientRequest clientRequest) {
        return modelMapper.map(clientRequest, Client.class);
    }
}
