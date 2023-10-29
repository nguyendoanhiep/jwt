package com.tom.restaurant.repository;

import com.tom.restaurant.entity.VoucherCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VoucherCustomerRepository extends JpaRepository<VoucherCustomer,Long> {

    @Modifying
    @Query("UPDATE VoucherCustomer vc SET vc.numberPhone = :newNumberPhone WHERE vc.numberPhone = :oldNumberPhone")
    void updateNumberPhone(String oldNumberPhone, String newNumberPhone);

    @Query("Select vc from VoucherCustomer vc where vc.numberPhone = :numberPhone and vc.voucherId = :voucherId")
    VoucherCustomer checkExits(String numberPhone , Long voucherId);
}
