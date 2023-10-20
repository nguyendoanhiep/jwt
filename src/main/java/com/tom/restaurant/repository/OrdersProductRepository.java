package com.tom.restaurant.repository;

import com.tom.restaurant.entity.OrdersProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersProductRepository extends JpaRepository<OrdersProduct,Long> {
}
