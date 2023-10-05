package com.tom.restaurant.controller;

import com.tom.restaurant.entity.dto.OrdersDto;
import com.tom.restaurant.entity.dto.VoucherCodeDto;
import com.tom.restaurant.response.Response;
import com.tom.restaurant.service.VoucherCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/voucher-code")
public class VoucherCodeController {
    @Autowired
    VoucherCodeService voucherCodeService;

    @GetMapping("/getAll")
    public Response<?> getAll(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size) {
        return voucherCodeService.getAll(PageRequest.of(page - 1, size));
    }

    @PostMapping("/save")
    public Response<?> getAll(@RequestBody VoucherCodeDto voucherCodeDto) {
        return voucherCodeService.save(voucherCodeDto);
    }
}
