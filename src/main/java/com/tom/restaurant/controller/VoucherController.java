package com.tom.restaurant.controller;

import com.tom.restaurant.entity.dto.VoucherRequest;
import com.tom.restaurant.response.Response;
import com.tom.restaurant.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/voucher")
public class VoucherController {
    @Autowired
    VoucherService voucherService;

    @GetMapping("/getAll")
    public Response<?> getAll(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam String search,
                              @RequestParam Integer status
    ) {
        return voucherService.getAll(
                PageRequest.of(page - 1, size),
                search.equals("") ? null : search,
                status );
    }

    @PostMapping("/addOrUpdate")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response<?> addOrUpdate(@RequestBody VoucherRequest request) {
        return voucherService.save(request);
    }

    @GetMapping("/findByNumberPhone")
    public Response<?> findByNumberPhone(@RequestParam String numberPhone) {
        return voucherService.findByNumberPhone( numberPhone );
    }

    @PostMapping("/addVoucherForCustomer")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response<?> addVoucherForCustomer(@RequestParam List<String> numberPhoneList,
                                             @RequestParam Long voucherId) {
        return voucherService.addVoucherForCustomer( numberPhoneList ,voucherId);
    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response<?> delete(@RequestParam Long id) {
        return voucherService.delete(id);
    }
}
