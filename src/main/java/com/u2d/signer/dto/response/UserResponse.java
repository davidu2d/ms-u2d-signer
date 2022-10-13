package com.u2d.signer.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse implements Serializable {

    private static final long serialVersionUID = 5412567432437780901L;

    private String name;

    private String cpfCnpj;

    private String email;

    private List<PermissionResponse> permissions;
}
