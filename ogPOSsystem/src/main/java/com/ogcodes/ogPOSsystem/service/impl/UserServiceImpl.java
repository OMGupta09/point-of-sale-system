package com.ogcodes.ogPOSsystem.service.impl;

import com.ogcodes.ogPOSsystem.configuration.JwtProvider;
import com.ogcodes.ogPOSsystem.exceptions.UserException;
import com.ogcodes.ogPOSsystem.models.User;
import com.ogcodes.ogPOSsystem.repository.UserRepository;
import com.ogcodes.ogPOSsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;


    @Override
    public User getUserFromJwtToken(String token) throws UserException {

        String email=jwtProvider.validateToken(token);
        User user=userRepository.findByEmail(email);
        if(user == null) {
            throw new UserException("Invalid Token");
        }
        return user;
    }

    @Override
    public User getCurrentUser() throws UserException {
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        User user= userRepository.findByEmail(email);
        if(user == null) throw new UserException("User Not Found!");
        return user;
    }

    @Override
    public User getUserByEmail(String email) throws UserException {
        User user=userRepository.findByEmail(email);
        if(user == null) throw new UserException("User Not Found!");
        return user;
    }

    @Override
    public User getUserById(long Id) {

        return userRepository.findById(Id).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }
}
