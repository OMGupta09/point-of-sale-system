package com.ogcodes.ogPOSsystem.service;

import com.ogcodes.ogPOSsystem.exceptions.UserException;
import com.ogcodes.ogPOSsystem.models.User;

import java.util.List;

public interface UserService {
    User getUserFromJwtToken(String token) throws UserException;
    User getCurrentUser() throws UserException;
    User getUserByEmail(String email) throws UserException;
    User getUserById(long Id);
    List<User> getAllUsers();
}
