package com.u2d.signer.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder
public class ClientRequest implements Serializable {

    private static final long serialVersionUID = 7065062851446686612L;

    @NotBlank
    private String clientId;

    @NotBlank
    private String clientSecret;

    private String resourceIds;

    @NotBlank
    private String scope;

    @NotBlank
    private String authorizedGrantTypes;

    @NotBlank
    private String webServerRedirectUri;

    @NotBlank
    private String authorities;

    @NotBlank
    private Long accessTokenValidity;

    @NotBlank
    private Long refreshTokenValidity;

    @NotBlank
    private String additionalInformation;

    @NotBlank
    private String autoapprove;
}
