package org.chenemiken.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class SignupRequest {

    @NotBlank()
    @Email
    private String email;

    @NotBlank()
    private String password;
}
