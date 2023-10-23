package com.tom.restaurant.repository;

import com.tom.restaurant.entity.Voucher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    @Query(value = "select v from Voucher v where " +
            "(:name is null or v.name like %:name%) and " +
            "(:code is null or v.code like :code%) and " +
            "(:status is 0 or v.status =:status)")
    Page<Voucher> getAll(Pageable pageable, String name, String code, Integer status);

    @Query(value = "SELECT v FROM Voucher v join " +
            "VoucherCustomer vc on v.id= vc.voucherId join " +
            "Customer c on vc.numberPhone = c.numberPhone where " +
            "c.numberPhone= :numberPhone")
    List<Voucher> findByNumberPhone(String numberPhone);
}
