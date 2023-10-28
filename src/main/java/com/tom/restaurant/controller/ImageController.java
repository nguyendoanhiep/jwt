package com.tom.restaurant.controller;

import com.tom.restaurant.entity.Image;
import com.tom.restaurant.response.Response;
import com.tom.restaurant.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    ImageService imageService;

    @GetMapping("/getImageByProductId")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response<?> addOrUpdateImage(@RequestParam Long productId) {
        return imageService.getImageByProductId(productId);
    }

    @PostMapping("/setPriorityImage")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response<?> setPriorityImage(@RequestParam Long imageId ,
                                        @RequestParam Long productId  ) {
        return imageService.setPriorityImage(imageId,productId);
    }
    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response<?> setPriorityImage(@RequestBody Image image) {
        return imageService.add(image);
    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response<?> delete(@RequestParam Long id) {
        return imageService.delete(id);
    }
}
