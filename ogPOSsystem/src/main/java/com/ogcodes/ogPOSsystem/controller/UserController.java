package com.ogcodes.ogPOSsystem.controller;

import com.ogcodes.ogPOSsystem.exceptions.UserException;
import com.ogcodes.ogPOSsystem.mapper.UserMapper;
import com.ogcodes.ogPOSsystem.models.User;
import com.ogcodes.ogPOSsystem.payload.dto.Userdto;
import com.ogcodes.ogPOSsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    public ResponseEntity<Userdto> getUserProfile(
            @RequestHeader("Authorization") String jwt
    ) throws UserException {
        User user=userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(UserMapper.toDTO(user)    );
    }
}
