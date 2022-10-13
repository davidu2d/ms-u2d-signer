package com.u2d.signer.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionRequest implements Serializable {

    private static final long serialVersionUID = 2646657990595661458L;

    @NotBlank
    private String description;
}
