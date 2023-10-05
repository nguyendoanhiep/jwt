package com.tom.restaurant.repository;

import com.tom.restaurant.entity.VoucherCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherCodeRepository extends JpaRepository<VoucherCode,Long> {

}
