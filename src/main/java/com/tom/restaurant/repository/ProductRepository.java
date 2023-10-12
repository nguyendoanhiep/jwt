package com.tom.restaurant.repository;

import com.tom.restaurant.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value = "select pr from Product pr " +
            "where :name is null or (pr.name like %:name%) " +
            "and :type is null or (pr.type =:type) " +
            "and :status is null or (pr.status =:status)")
    Page<Product> getAll(Pageable pageable , @Param("name") String name ,@Param("status") Integer status ,@Param("type") Integer type);

    @Query(value = "select pr from Product pr where pr.id in (:listProductId)")
    List<Product> findByListId(List<Long>listProductId);
}
