package com.tom.restaurant.repository;

import com.tom.restaurant.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
