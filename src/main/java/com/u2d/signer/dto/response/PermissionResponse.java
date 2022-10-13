package com.u2d.signer.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionResponse implements Serializable {

    private static final long serialVersionUID = -2324651905276937540L;

    private String description;
}
