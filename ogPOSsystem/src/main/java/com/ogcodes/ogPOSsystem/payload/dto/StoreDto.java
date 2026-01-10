package com.ogcodes.ogPOSsystem.payload.dto;

import com.ogcodes.ogPOSsystem.domain.StoreStatus;
import com.ogcodes.ogPOSsystem.models.StoreContact;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StoreDto {
    private Long id;

    private String brand;

    private Userdto storeAdmin;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String description;

    private String storeType;

    private StoreStatus status;


    private StoreContact contact;
}
