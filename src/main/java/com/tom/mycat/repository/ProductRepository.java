package com.tom.mycat.repository;

import com.tom.mycat.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value = "select pr from Product pr where :search is null or pr.name like %:search% ")
    Page<Product> getAll(Pageable pageable , String search);
}
