package com.tom.restaurant.controller;

import com.tom.restaurant.entity.dto.CustomerRequest;
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response<?> getAll(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam String search,
                              @RequestParam Integer status) {
        return customerService.getAll(PageRequest.of(page - 1, size),
                search.equals("") ? null : search, status);
    }
    @GetMapping("/findById")
    public Response<?> findById(@RequestParam Long id) {
        return customerService.findById(id);
    }

    @GetMapping("/getAllByVoucherId")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response<?> getAllByVoucherId(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam String search,
                              @RequestParam Long voucherId) {
            return customerService.getAllByVoucherId(PageRequest.of(page - 1, size),
                search.equals("") ? null : search, voucherId);
    }

    @PostMapping("/addOrUpdate")
    public Response<?> addOrUpdate(@RequestBody CustomerRequest request) {
        return customerService.addOrUpdate(request);
    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response<?> delete(@RequestParam Long id) {
        return customerService.delete(id);
    }
}
