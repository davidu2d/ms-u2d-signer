package com.u2d.signer.service;

import com.u2d.signer.dto.request.PayloadRequest;
import com.u2d.signer.dto.response.PayloadResponse;

public interface PayloadService {

    PayloadResponse createPayload(PayloadRequest payloadRequest);
}
