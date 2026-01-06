package com.ogcodes.ogPOSsystem.payload.dto;

import com.ogcodes.ogPOSsystem.domain.UserRole;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Userdto {

    private long id;

    private String username;

    private String email;

    private String password;

    private String phone;

    private UserRole role;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLogin;
}
