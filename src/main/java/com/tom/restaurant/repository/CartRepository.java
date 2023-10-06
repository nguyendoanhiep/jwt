package com.tom.restaurant.repository;

import com.tom.restaurant.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaRepository<Cart,Long> {
    @Query(value = "select c from Cart c where c.customerId = :customerId and (:voucherCodeId is null or c.voucherCodeId = :voucherCodeId) ")
    Cart showCart(Long customerId, Long voucherCodeId);
    @Query(value = "select c from Cart c where c.customerId = :customerId")
    Cart findByCustomerId(Long customerId);
}
