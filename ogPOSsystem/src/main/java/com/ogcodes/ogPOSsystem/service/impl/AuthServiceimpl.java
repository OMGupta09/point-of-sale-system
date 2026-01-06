package com.ogcodes.ogPOSsystem.service.impl;

import com.ogcodes.ogPOSsystem.configuration.JwtProvider;
import com.ogcodes.ogPOSsystem.domain.UserRole;
import com.ogcodes.ogPOSsystem.exceptions.UserException;
import com.ogcodes.ogPOSsystem.mapper.UserMapper;
import com.ogcodes.ogPOSsystem.models.User;
import com.ogcodes.ogPOSsystem.payload.dto.Userdto;
import com.ogcodes.ogPOSsystem.payload.response.AuthResponse;
import com.ogcodes.ogPOSsystem.repository.UserRepository;
import com.ogcodes.ogPOSsystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthServiceimpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserImplementation customUserImplementation;
    @Override
    public AuthResponse signup(Userdto userdto) throws UserException {
        User user= userRepository.findByEmail(userdto.getEmail());
        if(user != null)
        {
            throw new UserException("Email ID already exists!");
        }
        if(userdto.getRole().equals(UserRole.ROLE_ADMIN)) {
            throw new UserException("Role Admin isn't allowed!");
        }

        User newUser=new User();
        newUser.setEmail(userdto.getEmail());
        newUser.setPassword(passwordEncoder.encode(userdto.getPassword()));
        newUser.setRole(userdto.getRole());
        newUser.setUsername(userdto.getUsername());
        newUser.setPhone(userdto.getPhone());
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setLastLogin(LocalDateTime.now());
        newUser.setUpdatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(newUser);


        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userdto.getEmail(),userdto.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt=jwtProvider.generateToken(authentication);

        AuthResponse authResponse=new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Registered Successfully!");
        authResponse.setUser(UserMapper.toDTO(savedUser));

        return authResponse;

    }

    @Override
    public AuthResponse login(Userdto userdto) throws UserException {
        String email=userdto.getEmail();
        String password=userdto.getPassword();

        Authentication authentication= authenticate(email,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        String role=authorities.iterator().next().getAuthority();

        String jwt=jwtProvider.generateToken(authentication);

        User user=userRepository.findByEmail(email);

        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        AuthResponse authResponse=new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Login Successful!");
        authResponse.setUser(UserMapper.toDTO(user));
        return authResponse;
    }

    private Authentication authenticate(String email, String password) throws UserException {
        UserDetails userDetails=customUserImplementation.loadUserByUsername(email);

        if(userDetails==null) {
            throw new UserException("Email ID" + email + " does not exist in the Database");
        }

        if(!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new UserException("Password doesn't match!");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
