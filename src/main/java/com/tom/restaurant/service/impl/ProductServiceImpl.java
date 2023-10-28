package com.tom.restaurant.service.impl;

import com.tom.restaurant.entity.Image;
import com.tom.restaurant.entity.Product;
import com.tom.restaurant.entity.dto.ProductRequest;
import com.tom.restaurant.repository.ImageRepository;
import com.tom.restaurant.repository.ProductRepository;
import com.tom.restaurant.response.Response;
import com.tom.restaurant.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ImageRepository imageRepository;

    @Override
    public Response<?> getAll(Pageable pageable, String name, Integer status, Integer type) {
        try {
            return Response.SUCCESS(productRepository.getAll(pageable, name, status, type));
        } catch (Exception e) {
            log.info(e.getMessage());
            return Response.FAIL(false);
        }
    }

    @Override
    public Response<?> addOrUpdate(ProductRequest request) {
        try {
            List<Image> ls = imageRepository.saveAll(request.getImages());
            Product product = Product.builder()
                    .id(request.getId())
                    .name(request.getName())
                    .price(request.getPrice())
                    .type(request.getType())
                    .status(request.getStatus())
                    .description(request.getDescription())
                    .userId(1L)
                    .createDate(request.getCreateDate() != null ? request.getCreateDate() : new Date())
                    .modifiedDate(new Date())
                    .images(ls)
                    .build();
            productRepository.save(product);
            return Response.SUCCESS(product.getId());
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            return Response.FAIL(false);
        }
    }

    @Override
    public Response<?> delete(Long id) {
        try {
            productRepository.deleteById(id);
            return Response.SUCCESS(true);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.FAIL(false);
        }
    }
}
