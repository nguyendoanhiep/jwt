package com.tom.restaurant.repository;

import com.tom.restaurant.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaRepository<Cart,Long> {
    @Query(value = "select c from Cart c where c.customerId = :customerId and (:voucherId is null or c.voucherId = :voucherId) ")
    Cart showCart(Long customerId, Long voucherId);
    @Query(value = "select c from Cart c where c.customerId = :customerId")
    Cart findByCustomerId(Long customerId);
}
