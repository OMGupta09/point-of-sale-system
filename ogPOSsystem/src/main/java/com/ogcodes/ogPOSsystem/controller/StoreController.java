package com.ogcodes.ogPOSsystem.controller;

import com.ogcodes.ogPOSsystem.exceptions.UserException;
import com.ogcodes.ogPOSsystem.models.User;
import com.ogcodes.ogPOSsystem.payload.dto.StoreDto;
import com.ogcodes.ogPOSsystem.payload.response.ApiResponse;
import com.ogcodes.ogPOSsystem.service.StoreService;
import com.ogcodes.ogPOSsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreController {

    private final StoreService storeService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<StoreDto> createStore(@RequestBody StoreDto storeDto,
                                                @RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(storeService.createStore(storeDto, user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreDto> getStoreById(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt) throws Exception {

        return ResponseEntity.ok(storeService.getStoreById(id));
    }

    @GetMapping()
    public ResponseEntity<List<StoreDto>> getAllStores(
            @RequestHeader("Authorization") String jwt) throws Exception {

        return ResponseEntity.ok(storeService.getAllStores());
    }

    @GetMapping("/admin")
    public ResponseEntity<StoreDto> getStoreByAdmin() throws UserException {
        return ResponseEntity.ok(storeService.getStoreByAdmin());
    }


    @GetMapping("/employee")
    public ResponseEntity<StoreDto> getStoreByEmployee(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt) throws Exception {

        return ResponseEntity.ok(storeService.getStoreByEmployee());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreDto> updateStore(
            @PathVariable Long id,
            @RequestBody StoreDto storeDto) throws Exception {

        return ResponseEntity.ok(storeService.updateStore(id, storeDto));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteStore(@PathVariable Long id) throws Exception {

        storeService.deleteStore(id);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Successfully Deleted the Store!");

        return ResponseEntity.ok(apiResponse);
    }


}
