package com.ogcodes.ogPOSsystem.controller;

import com.ogcodes.ogPOSsystem.exceptions.UserException;
import com.ogcodes.ogPOSsystem.payload.dto.Userdto;
import com.ogcodes.ogPOSsystem.payload.response.AuthResponse;
import com.ogcodes.ogPOSsystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;



//api endpoint: /auth/signup
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signupHandler(
            @RequestBody Userdto userdto
            ) throws UserException {
        return ResponseEntity.ok(
                authService.signup(userdto)
        );
    }

    //api endpoint: /auth/login
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginHandler(
            @RequestBody Userdto userdto
    ) throws UserException {
        return ResponseEntity.ok(
                authService.login(userdto)
        );
    }
}
