package com.tom.restaurant.repository;

import com.tom.restaurant.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image,Long> {
    @Query(value = "SELECT * FROM image i join product_image pi on i.id = pi.image_id where pi.product_id = :productId order by i.create_date desc",nativeQuery = true)
    List<Image>getImageByProductId(Long productId);
}
