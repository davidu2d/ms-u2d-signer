package com.u2d.signer.service;

import com.u2d.signer.dto.request.ClientRequest;
import com.u2d.signer.dto.response.ClientResponse;

public interface ClientService {

    ClientResponse createClient(ClientRequest clientRequest);
}
