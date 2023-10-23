package com.tom.restaurant.controller;

import com.tom.restaurant.entity.dto.CustomerDto;
import com.tom.restaurant.entity.dto.ProductDto;
import com.tom.restaurant.response.Response;
import com.tom.restaurant.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/getAll")
    public Response<?> getAll(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam String search,
                              @RequestParam Integer status) {
        return customerService.getAll(PageRequest.of(page - 1, size),
                search.equals("") ? null : search, status);
    }

    @PostMapping("/addOrUpdate")
    public Response<?> addOrUpdate(@RequestBody CustomerDto dto) {
        return customerService.addOrUpdate(dto);
    }

    @GetMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response<?> delete(@RequestParam Long id) {
        return customerService.delete(id);
    }
}
