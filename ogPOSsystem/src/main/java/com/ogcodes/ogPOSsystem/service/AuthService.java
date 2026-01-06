package com.ogcodes.ogPOSsystem.service;

import com.ogcodes.ogPOSsystem.exceptions.UserException;
import com.ogcodes.ogPOSsystem.payload.dto.Userdto;
import com.ogcodes.ogPOSsystem.payload.response.AuthResponse;

public interface AuthService {

    AuthResponse signup(Userdto userdto) throws UserException;

    AuthResponse login(Userdto userdto) throws UserException;


}
