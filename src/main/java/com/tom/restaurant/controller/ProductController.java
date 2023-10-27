package com.tom.restaurant.controller;

import com.tom.restaurant.entity.dto.ProductRequest;
import com.tom.restaurant.response.Response;
import com.tom.restaurant.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;
    @GetMapping("/getAll")
    public Response<?> getAll(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam String name,
                              @RequestParam Integer status,
                              @RequestParam Integer type
    ) {
        return productService.getAll(PageRequest.of(page - 1, size),
                name.equals("") ? null : name,
                status,
                type);
    }

    @PostMapping("/addOrUpdate")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response<?> addOrUpdate(@RequestBody ProductRequest request) {
        return productService.addOrUpdate(request);
    }
    @GetMapping("/getImageByProductId")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response<?> addOrUpdateImage(@RequestParam Long productId) {
        return productService.getImageByProductId(productId);
    }

    @GetMapping("/setPriorityImage")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response<?> setPriorityImage(@RequestParam Long imageId ,
                                        @RequestParam Long productId  ) {
        return productService.setPriorityImage(imageId,productId);
    }

    @PostMapping("/deleteImageOfProduct")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response<?> deleteImageOfProduct(@RequestParam Long imageId) {
        return productService.deleteImageOfProduct(imageId);
    }
    @PostMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response<?> delete(@RequestParam Long id) {
        return productService.delete(id);
    }

}
