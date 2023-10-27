package com.tom.restaurant.repository;

import com.tom.restaurant.entity.VoucherCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface VoucherCustomerRepository extends JpaRepository<VoucherCustomer,Long> {

    @Modifying
    @Query("UPDATE VoucherCustomer vc SET vc.numberPhone = :newNumberPhone WHERE vc.numberPhone = :oldNumberPhone")
    void updateNumberPhone(String oldNumberPhone, String newNumberPhone);
}
