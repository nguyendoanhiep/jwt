package com.tom.mycat.controller;

import com.tom.mycat.entity.dto.FormRegister;
import com.tom.mycat.entity.dto.ProductDto;
import com.tom.mycat.response.Response;
import com.tom.mycat.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
                              @RequestParam String search
    ) {
        return productService.getAll(PageRequest.of(page - 1, size), search);
    }

    @PostMapping("/addOrUpdate")
    public Response<?> register(@RequestBody ProductDto dto) {
        return productService.addOrUpdate(dto);
    }
}
