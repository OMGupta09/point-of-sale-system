package com.ogcodes.ogPOSsystem.controller;

import com.ogcodes.ogPOSsystem.exceptions.UserException;
import com.ogcodes.ogPOSsystem.mapper.UserMapper;
import com.ogcodes.ogPOSsystem.models.User;
import com.ogcodes.ogPOSsystem.payload.dto.Userdto;
import com.ogcodes.ogPOSsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/profile")
    public ResponseEntity<Userdto> getUserProfile(
            @RequestHeader("Authorization") String jwt
    ) throws UserException {
        User user=userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(UserMapper.toDTO(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Userdto> getUserById(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws UserException {
        User user=userService.getUserById(id);
        if(user==null)
        {
            throw new UserException("User Not Found");
        }
        return ResponseEntity.ok(UserMapper.toDTO(user));
    }
}
