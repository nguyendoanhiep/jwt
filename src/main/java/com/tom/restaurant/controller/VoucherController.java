package com.tom.restaurant.controller;

import com.tom.restaurant.entity.dto.VoucherDto;
import com.tom.restaurant.response.Response;
import com.tom.restaurant.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
                              @RequestParam String name,
                              @RequestParam String code,
                              @RequestParam Integer status,
                              @RequestParam String ascOrDesc
    ) {
        return voucherService.getAll(
                PageRequest.of(page - 1, size),
                name.equals("") ? null : name,
                code.equals("") ? null : code,
                status );
    }

    @PostMapping("/addOrUpdate")
    public Response<?> getAll(@RequestBody VoucherDto voucherDto) {
        return voucherService.save(voucherDto);
    }

    @GetMapping("/findByNumberPhone")
    public Response<?> findByNumberPhone(@RequestParam String numberPhone) {
        return voucherService.findByNumberPhone( numberPhone );
    }

    @GetMapping("/addVoucherForCustomer")
    public Response<?> addVoucherForCustomer(@RequestParam List<String> numberPhoneList,
                                             @RequestParam Long voucherId) {
        return voucherService.addVoucherForCustomer( numberPhoneList ,voucherId);
    }

    @GetMapping("/delete")
    public Response<?> delete(@RequestParam Long id) {
        return voucherService.delete(id);
    }
}
