package com.tom.restaurant.repository;

import com.tom.restaurant.entity.VoucherCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherCodeRepository extends JpaRepository<VoucherCode,Long> {
    @Query(value = "select vc from VoucherCode vc where vc.id in (:listVoucherCodeId)")
    List<VoucherCode> findByListId(List<Long>listVoucherCodeId);
}
