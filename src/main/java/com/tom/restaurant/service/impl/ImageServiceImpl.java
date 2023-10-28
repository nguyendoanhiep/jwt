package com.tom.restaurant.service.impl;

import com.tom.restaurant.entity.Image;
import com.tom.restaurant.repository.ImageRepository;
import com.tom.restaurant.response.Response;
import com.tom.restaurant.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    ImageRepository imageRepository;
    @Override
    public Response<?> getImageByProductId(Long productId) {
        try {
            List<Image> images = imageRepository.getImageByProductId(productId);
            return Response.SUCCESS(images);
        }catch (Exception e){
            log.info(e.getMessage());
            return Response.FAIL(false);
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
            return Response.FAIL(false);
        }
    }

    @Override
    public Response<?> delete(Long id) {
        try {
            imageRepository.deleteById(id);
            return Response.SUCCESS();
        }catch (Exception e){
            log.info(e.getMessage());
            return Response.FAIL(false);
        }
    }

    @Override
    public Response<?> add(Image image) {
        try {
            Image entity = imageRepository.save(image);
            return Response.SUCCESS(entity);
        }catch (Exception e){
            log.info(e.getMessage());
            return Response.FAIL(false);
        }
    }
}
