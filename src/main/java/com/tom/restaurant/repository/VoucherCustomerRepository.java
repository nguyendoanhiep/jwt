package com.tom.restaurant.repository;

import com.tom.restaurant.entity.VoucherCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherCustomerRepository extends JpaRepository<VoucherCustomer,Long> {
}
