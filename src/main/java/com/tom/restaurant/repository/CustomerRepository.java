package com.tom.restaurant.repository;

import com.tom.restaurant.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("select c from Customer c where c.numberPhone =:numberPhone")
    Customer findByNumberPhone(String numberPhone);

    @Query(value = "select c from Customer c where c.id in (:listCustomerId)")
    List<Customer> findByListId(List<Long> listCustomerId);
}
