package com.tom.restaurant.repository;

import com.tom.restaurant.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("select p from Product p where " +
            "(:name is null or p.name like %:name%) and " +
            "(:status is 0 or p.status = :status) and " +
            "(:type is 0 or p.type = :type) ")
    Page<Product> getAll(Pageable pageable , String name ,Integer status ,Integer type);

    @Query(value = "select pr from Product pr where pr.id in (:listProductId)")
    List<Product> findByListId(List<Long>listProductId);
}
