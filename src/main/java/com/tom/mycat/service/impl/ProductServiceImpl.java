package com.tom.mycat.service.impl;

import com.tom.mycat.entity.Product;
import com.tom.mycat.entity.dto.ProductDto;
import com.tom.mycat.repository.ProductRepository;
import com.tom.mycat.response.Response;
import com.tom.mycat.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Override
    public Response<?> getAll(Pageable pageable, String search) {
        try{
            return new Response<>(200,"Success", productRepository.getAll(pageable,search));
        }catch (Exception e){
            log.info(e.getMessage());
            return new Response<>(400,"Fail", null);
        }
    }

    @Override
    public Response<?> addOrUpdate(ProductDto dto) {
        try{
            Product product = Product.builder()
                    .id(dto.getId())
                    .name(dto.getName())
                    .price(dto.getPrice())
                    .type(dto.getType())
                    .status(dto.getStatus())
                    .description(dto.getDescription())
                    .createDate(dto.getCreateDate())
                    .modifiedDate(new Date())
                    .images(dto.getImages())
                    .build();
            return new Response<>(200,"Success", productRepository.save(product));
        }catch (Exception e){
            log.info(e.getMessage());
            return new Response<>(400,"Fail", null);
        }
    }
}
