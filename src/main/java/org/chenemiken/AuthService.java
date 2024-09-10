package org.chenemiken;

import org.chenemiken.models.Response;
import org.chenemiken.models.SignupRequest;
import org.chenemiken.models.SignupResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public Response<SignupResponse> signup(SignupRequest request){
        userRepository.findByEmail(request.getEmail()).ifPresent(user -> {
            throw new RuntimeException("Email already registered");
        });

        User newUser = new User();
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));

//    newUser = userRepository.save(newUser);

        return Response.<SignupResponse>builder()
                .success(true)
                .status("successful")
                .message("user registered successfully")
                .data(new SignupResponse(newUser.getEmail()))
                .build();
    }
}
