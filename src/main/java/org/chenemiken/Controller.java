package org.chenemiken;

import jakarta.validation.Valid;
import org.chenemiken.models.Response;
import org.chenemiken.models.SignupRequest;
import org.chenemiken.models.SignupResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
    final AuthService authService;

    public Controller(AuthService authService){
        this.authService = authService;
    }

    @GetMapping()
    void base(){}

    @GetMapping("/locked")
    void locked(){}

    @PostMapping("/signup")
    ResponseEntity<Response<SignupResponse>> signup(@Valid @RequestBody SignupRequest request){
        return new ResponseEntity<>(authService.signup(request), HttpStatus.OK);
    }
}
