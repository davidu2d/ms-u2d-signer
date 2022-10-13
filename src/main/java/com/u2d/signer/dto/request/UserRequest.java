package com.u2d.signer.dto.request;

import com.u2d.signer.annotations.password.Password;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest implements Serializable {

    private static final long serialVersionUID = 5872138318939213262L;

    @NotBlank
    private String name;

    @NotBlank
    @Size(min = 11, max = 14)
    private String cpfCnpj;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Password
    private String password;

    @NotNull
    private List<PermissionRequest> permissions;
}
