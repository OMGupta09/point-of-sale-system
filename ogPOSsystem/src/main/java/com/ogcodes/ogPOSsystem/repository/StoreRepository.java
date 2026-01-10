package com.ogcodes.ogPOSsystem.repository;

import com.ogcodes.ogPOSsystem.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long>{
    Store findByStoreAdmin(Long adminId);
}

