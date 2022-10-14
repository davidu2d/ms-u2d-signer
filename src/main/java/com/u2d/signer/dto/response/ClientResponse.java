package com.u2d.signer.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ClientResponse implements Serializable {
    private static final long serialVersionUID = -8003673610247187813L;

    private String clientId;
}
