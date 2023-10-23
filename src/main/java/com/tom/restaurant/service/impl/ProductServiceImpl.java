package com.tom.restaurant.service.impl;

import com.tom.restaurant.entity.Image;
import com.tom.restaurant.entity.Product;
import com.tom.restaurant.entity.dto.ProductDto;
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
            return Response.FAIL();
        }
    }

    @Override
    public Response<?> addOrUpdate(ProductDto dto) {
        try {
            List<Image> ls = imageRepository.saveAll(dto.getImages());
            Product product = Product.builder()
                    .id(dto.getId())
                    .name(dto.getName())
                    .price(dto.getPrice())
                    .type(dto.getType())
                    .status(dto.getStatus())
                    .description(dto.getDescription())
                    .userId(1L)
                    .createDate(dto.getCreateDate() != null ? dto.getCreateDate() : new Date())
                    .modifiedDate(new Date())
                    .images(ls)
                    .build();
            productRepository.save(product);
            return Response.SUCCESS(product.getId());
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            return Response.FAIL();
        }
    }

    @Override
    public Response<?> getImageByProductId(Long productId) {
       try {
           List<Image> images = imageRepository.getImageByProductId(productId);
           return Response.SUCCESS(images);
       }catch (Exception e){
           log.info(e.getMessage());
           return Response.FAIL();
       }
    }

    @Override
    public Response<?> setPriorityImage(Long imageId, Long productId) {
        try {
            List<Image> images = imageRepository.getImageByProductId(productId);
            images.forEach(data -> {
                if(data.getId().equals(imageId)){
                    data.setPriority(1);
                }else {
                    data.setPriority(0);
                }
            });
            imageRepository.saveAll(images);
            return Response.SUCCESS();
        }catch (Exception e){
            log.info(e.getMessage());
            return Response.FAIL();
        }
    }

    @Override
    public Response<?> deleteImageOfProduct(Long imageId) {
        try {
            imageRepository.deleteById(imageId);
            return Response.SUCCESS();
        }catch (Exception e){
            log.info(e.getMessage());
            return Response.FAIL();
        }
    }

    @Override
    public Response<?> delete(Long id) {
        try {
            productRepository.deleteById(id);
            return Response.SUCCESS(true);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.FAIL();
        }
    }
}
