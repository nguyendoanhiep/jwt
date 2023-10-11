package com.tom.restaurant.controller;

import com.tom.restaurant.entity.dto.VoucherDto;
import com.tom.restaurant.response.Response;
import com.tom.restaurant.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/voucher")
public class VoucherController {
    @Autowired
    VoucherService voucherService;

    @GetMapping("/getAll")
    public Response<?> getAll(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam String name,
                              @RequestParam String code,
                              @RequestParam Integer status,
                              @RequestParam String ascOrDesc
    ) {
        Sort sort = ascOrDesc.equals("asc") ? Sort.by("value").ascending() : Sort.by("value").descending();
        return voucherService.getAll(
                ascOrDesc.isEmpty() ?
                PageRequest.of(page - 1, size) :
                PageRequest.of(page - 1, size, sort),
                name, code, status);
    }

    @PostMapping("/addOrUpdate")
    public Response<?> getAll(@RequestBody VoucherDto voucherDto) {
        return voucherService.save(voucherDto);
    }
}