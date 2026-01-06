package com.ogcodes.ogPOSsystem.payload.response;

import com.ogcodes.ogPOSsystem.payload.dto.Userdto;
import lombok.Data;

@Data
public class AuthResponse {

    private String jwt;
    private String message;
    private Userdto user;
}
