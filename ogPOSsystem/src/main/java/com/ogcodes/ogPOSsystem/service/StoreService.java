package com.ogcodes.ogPOSsystem.service;

import com.ogcodes.ogPOSsystem.domain.StoreStatus;
import com.ogcodes.ogPOSsystem.exceptions.UserException;
import com.ogcodes.ogPOSsystem.models.Store;
import com.ogcodes.ogPOSsystem.models.User;
import com.ogcodes.ogPOSsystem.payload.dto.StoreDto;

import java.util.List;

public interface StoreService {

    StoreDto createStore(StoreDto storeDto, User user);

    StoreDto getStoreById(Long id) throws UserException;

    List<StoreDto> getAllStores();

    StoreDto getStoreByAdmin() throws UserException;

    StoreDto updateStore(Long id, StoreDto storeDto) throws UserException;

    void deleteStore(Long id) throws UserException;

    StoreDto getStoreByEmployee();

    StoreDto moderateStore(Long id, StoreStatus status) throws UserException;
}
