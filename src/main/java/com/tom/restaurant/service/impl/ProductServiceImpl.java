package com.tom.restaurant.service.impl;

import com.tom.restaurant.entity.Product;
import com.tom.restaurant.entity.dto.ProductDto;
import com.tom.restaurant.repository.ProductRepository;
import com.tom.restaurant.response.Response;
import com.tom.restaurant.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Response<?> getAll(Pageable pageable, String name, Integer status, Integer type) {
        try {
            return Response.SUCCESS(productRepository.getAll(pageable, name, status, type));
        } catch (Exception e) {
            log.info(e.getMessage());
            return Response.FAIL();
        }
    }

    @Override
    public Response<?> addOrUpdate(ProductDto dto) {
        try {
            Product product = productRepository.save(Product.builder()
                    .id(dto.getId())
                    .name(dto.getName())
                    .price(dto.getPrice())
                    .type(dto.getType())
                    .status(dto.getStatus())
                    .description(dto.getDescription())
                    .userId(1L)
                    .createDate(dto.getCreateDate() != null ? dto.getCreateDate() : new Date())
                    .modifiedDate(new Date())
                    .images(dto.getImages())
                    .build());
            return Response.SUCCESS(product.getId());
        } catch (Exception e) {
            log.info(e.getMessage());
            return Response.FAIL();
        }
    }
}
