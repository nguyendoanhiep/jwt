package com.tom.restaurant.service.impl;

import com.tom.restaurant.entity.Voucher;
import com.tom.restaurant.entity.dto.VoucherDto;
import com.tom.restaurant.repository.CustomerRepository;
import com.tom.restaurant.repository.ProductRepository;
import com.tom.restaurant.repository.VoucherRepository;
import com.tom.restaurant.response.Response;
import com.tom.restaurant.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Service
public class VoucherServiceImp implements VoucherService {
    @Autowired
    VoucherRepository voucherRepository;

    @Override
    public Response<?> getAll(Pageable pageable, String name, String code, Integer status) {
        try {
            Page<Voucher> listVoucher = voucherRepository.getAll(pageable,name,code,status);
            return Response.SUCCESS(listVoucher);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.FAIL();
        }
    }

    @Override
    public Response<?> save(VoucherDto voucherDto) {
        try {
           Voucher voucher =  voucherRepository.save(Voucher
                    .builder()
                    .id(voucherDto.getId())
                    .code(voucherDto.getId() == null ? generateRandomCode() : voucherDto.getCode())
                    .name(voucherDto.getName())
                    .value(voucherDto.getValue())
                    .userCreateId(voucherDto.getUserCreateId())
                    .status(voucherDto.getStatus())
                    .voucherStartDate(voucherDto.getVoucherStartDate())
                    .voucherExpirationDate(voucherDto.getVoucherExpirationDate())
                    .createDate(new Date())
                    .modifiedDate(new Date())
                    .build());
            return Response.SUCCESS(voucher.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return Response.FAIL();
        }
    }

    private String generateRandomCode() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = dateFormat.format(new Date());
        String candidateChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            int index = random.nextInt(candidateChars.length());
            sb.append(candidateChars.charAt(index));
        }
        return sb.append(timestamp).toString();
    }
}
