package com.ogcodes.ogPOSsystem.service.impl;

import com.ogcodes.ogPOSsystem.exceptions.UserException;
import com.ogcodes.ogPOSsystem.mapper.StoreMapper;
import com.ogcodes.ogPOSsystem.models.Store;
import com.ogcodes.ogPOSsystem.models.User;
import com.ogcodes.ogPOSsystem.payload.dto.StoreDto;
import com.ogcodes.ogPOSsystem.repository.StoreRepository;
import com.ogcodes.ogPOSsystem.service.StoreService;
import com.ogcodes.ogPOSsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final UserService userService;

    @Override
    public StoreDto createStore(StoreDto storeDto, User user) {
        Store store= StoreMapper.toEntity(storeDto, user);

        return StoreMapper.toDTO(storeRepository.save(store));
    }



    @Override
    public StoreDto getStoreById(Long id) throws Exception {
        Store store=storeRepository.findById(id).orElseThrow(
                ()-> new Exception("Store Not Found!")
        );
        return StoreMapper.toDTO(store);
    }

    @Override
    public List<StoreDto> getAllStores() {
        List<Store> dtos = storeRepository.findAll();
        return dtos.stream().map(StoreMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Store getStoreByAdmin() throws UserException {
        User admin=userService.getCurrentUser();
        return storeRepository.findByStoreAdmin(admin.getId());
    }

    @Override
    public StoreDto updateStore(Long id, StoreDto storeDto) {
        return null;
    }

    @Override
    public StoreDto deleteStore() {
        return null;
    }

    @Override
    public StoreDto getStoreByEmployee() {
        return null;
    }
}
