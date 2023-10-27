package com.tom.restaurant.repository;

import com.tom.restaurant.entity.Orders;
import com.tom.restaurant.entity.dto.OrdersRequest;
import com.tom.restaurant.entity.dto.OrdersResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface OrderRepository extends JpaRepository<Orders,Long> {
    @Query("select new com.tom.restaurant.entity.dto.OrdersResponse(o.id,c.name,o.code,o.numberPhone,v.name,o.originalTotalValue,o.discountAmount,o.totalValue,o.status,o.createDate) " +
            "from Orders o left join Customer c on o.numberPhone = c.numberPhone left join Voucher v on v.id = o.voucherId where " +
            "(:search is null or o.code like :search% or o.numberPhone like :search%) and " +
            "(:status = 0 or o.status = :status) order by o.createDate desc")
    Page<OrdersResponse> getAll(Pageable pageable, String search , Integer status);

    @Modifying
    @Query("UPDATE Orders o SET o.numberPhone = :newNumberPhone WHERE o.numberPhone = :oldNumberPhone")
    void updateNumberPhone(String oldNumberPhone, String newNumberPhone);
}
