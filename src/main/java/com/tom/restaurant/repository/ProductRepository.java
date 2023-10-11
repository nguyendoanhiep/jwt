package com.tom.restaurant.repository;

import com.tom.restaurant.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value = "select pr from Product pr where (:search is null or pr.name like %:search% ) and (:status is null or pr.status = :status) and (type is null or pr.type = :type) ")
    Page<Product> getAll(Pageable pageable , String search , Integer status , Long type);

    @Query(value = "select pr from Product pr where pr.id in (:listProductId)")
    List<Product> findByListId(List<Long>listProductId);
}
