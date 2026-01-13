package com.ogcodes.ogPOSsystem.repository;

import com.ogcodes.ogPOSsystem.models.Store;
import com.ogcodes.ogPOSsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Store findByStoreAdmin(User storeAdmin);
}
