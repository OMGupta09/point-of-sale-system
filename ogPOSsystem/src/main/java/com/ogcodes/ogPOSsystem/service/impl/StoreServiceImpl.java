package com.ogcodes.ogPOSsystem.service.impl;

import com.ogcodes.ogPOSsystem.domain.StoreStatus;
import com.ogcodes.ogPOSsystem.exceptions.UserException;
import com.ogcodes.ogPOSsystem.mapper.StoreMapper;
import com.ogcodes.ogPOSsystem.models.Store;
import com.ogcodes.ogPOSsystem.models.StoreContact;
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

    // ================= CREATE =================
    @Override
    public StoreDto createStore(StoreDto storeDto, User user) {
        Store store = StoreMapper.toEntity(storeDto, user);
        return StoreMapper.toDTO(storeRepository.save(store));
    }

    // ================= READ =================
    @Override
    public StoreDto getStoreById(Long id) throws UserException {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new UserException("Store Not Found"));
        return StoreMapper.toDTO(store);
    }

    @Override
    public List<StoreDto> getAllStores() {
        return storeRepository.findAll()
                .stream()
                .map(StoreMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StoreDto getStoreByAdmin() throws UserException {

        User admin = userService.getCurrentUser();

        Store store = storeRepository.findByStoreAdmin(admin);

        if (store == null) {
            throw new UserException("Store Not Found for this admin");
        }

        return StoreMapper.toDTO(store);
    }





    // ================= UPDATE =================
    @Override
    public StoreDto updateStore(Long id, StoreDto storeDto) throws UserException {

        User admin = userService.getCurrentUser();
        Store existing = storeRepository.findByStoreAdmin(admin);

        if (existing == null) {
            throw new UserException("Store Not Found");
        }

        existing.setBrand(storeDto.getBrand());
        existing.setDescription(storeDto.getDescription());

        if (storeDto.getStoreType() != null) {
            existing.setStoreType(storeDto.getStoreType());
        }

        if (storeDto.getContact() != null) {
            StoreContact contact = StoreContact.builder()
                    .address(storeDto.getContact().getAddress())
                    .phone(storeDto.getContact().getPhone())
                    .email(storeDto.getContact().getEmail())
                    .build();
            existing.setContact(contact);
        }

        return StoreMapper.toDTO(storeRepository.save(existing));
    }

    // ================= DELETE =================
    @Override
    public void deleteStore(Long id) throws UserException {

        User admin = userService.getCurrentUser();

        Store store = storeRepository.findByStoreAdmin(admin);

        if (store == null) {
            throw new UserException("Store Not Found");
        }

        storeRepository.delete(store);
    }

    // ================= EMPLOYEE =================
    @Override
    public StoreDto getStoreByEmployee() {
        // TODO: implement when employee-store mapping is added
        return null;
    }

    // ================= ADMIN MODERATION =================
    @Override
    public StoreDto moderateStore(Long id, StoreStatus status) throws UserException {

        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new UserException("Store Not Found"));

        store.setStatus(status);
        return StoreMapper.toDTO(storeRepository.save(store));
    }
}
