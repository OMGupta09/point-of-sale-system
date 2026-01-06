package com.ogcodes.ogPOSsystem.mapper;

import com.ogcodes.ogPOSsystem.models.User;
import com.ogcodes.ogPOSsystem.payload.dto.Userdto;

public class UserMapper {
    public static Userdto toDTO(User savedUser){
        Userdto userdto= new Userdto();
        userdto.setId(savedUser.getId());
        userdto.setUsername(savedUser.getUsername());
        userdto.setPhone(savedUser.getPhone());
        userdto.setEmail(savedUser.getEmail());
        userdto.setRole(savedUser.getRole());
        userdto.setCreatedAt(savedUser.getCreatedAt());
        userdto.setUpdatedAt(savedUser.getUpdatedAt());
        userdto.setLastLogin(savedUser.getLastLogin());

        return userdto;


    }

}
