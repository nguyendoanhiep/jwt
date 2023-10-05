package com.tom.restaurant.controller;

import com.tom.restaurant.entity.dto.OrdersDto;
import com.tom.restaurant.response.Response;
import com.tom.restaurant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/getAll")
    public Response<?> getAll(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size) {
        return orderService.getAll(PageRequest.of(page - 1, size));
    }

    @PostMapping("/save")
    public Response<?> getAll(@RequestBody OrdersDto ordersDto) {
        return orderService.save(ordersDto);
    }
}