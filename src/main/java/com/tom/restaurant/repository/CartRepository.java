package com.tom.restaurant.repository;

import com.tom.restaurant.entity.Cart;
import com.tom.restaurant.entity.dto.CartResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Long> {
//    @Query(value = "select c from Cart c where c.customerId = :customerId and (:voucherId is null or c.voucherId = :voucherId) ")
//    Cart showCart(Long customerId, Long voucherId);
    @Query(value = "select c from Cart c where c.customerId = :customerId and c.productId = :productId")
    Cart findProductOfCustomerId(Long customerId , Long productId);
    @Query(value = "select c from Cart c where c.customerId = :customerId")
    List<Cart> findByCustomerId(Long customerId);
    @Query(value = "select new com.tom.restaurant.entity.dto.CartResponse(p.id,p.name,c.quantity,p.price,(p.price * c.quantity)) from Cart c join Product p on c.productId = p.id where c.customerId=:customerId")
    List<CartResponse> getCartResponse(Long customerId);
}
