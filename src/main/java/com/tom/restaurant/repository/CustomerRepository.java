package com.tom.restaurant.repository;

import com.tom.restaurant.entity.Customer;
import com.tom.restaurant.entity.dto.CustomerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("select c from Customer c where c.numberPhone =:numberPhone")
    Customer findByNumberPhone(String numberPhone);

    @Query(value = "select c from Customer c where " +
            "(:search is null or c.name like :search% or c.numberPhone like :search%) and " +
            "(:status is 0 or c.status = :status)")
    Page<Customer> getAll(Pageable pageable, String search, Integer status);

    @Query( "select new com.tom.restaurant.entity.dto.CustomerResponse(c.id,c.name,c.numberPhone,c.status,c.loyaltyPoints,vc.voucherId) " +
            "from Customer c left join VoucherCustomer vc on c.numberPhone=vc.numberPhone where (:search is null or c.name like :search% or c.numberPhone like :search%) and c.status = 1 and vc.voucherId = :voucherId ")
    Page<CustomerResponse> getAllByVoucherIdExits(Pageable pageable, String search, Long voucherId);

    @Query("select new com.tom.restaurant.entity.dto.CustomerResponse(c.id,c.name,c.numberPhone,c.status,c.loyaltyPoints,vc.voucherId) " +
            "from Customer c left join VoucherCustomer vc on c.numberPhone=vc.numberPhone where (:search is null or c.name like :search% or c.numberPhone like :search%) and c.status = 1 and vc.voucherId is null")
    Page<CustomerResponse> getAllByVoucherIdNotExits(Pageable pageable, String search);

    @Modifying
    @Query("UPDATE Customer c SET c.numberPhone = :newNumberPhone WHERE c.numberPhone = :oldNumberPhone")
    void updateNumberPhone(String oldNumberPhone, String newNumberPhone);
}
