package com.tom.restaurant.service.impl;

import com.tom.restaurant.entity.VoucherCode;
import com.tom.restaurant.entity.dto.VoucherCodeDto;
import com.tom.restaurant.repository.CustomerRepository;
import com.tom.restaurant.repository.ProductRepository;
import com.tom.restaurant.repository.VoucherCodeRepository;
import com.tom.restaurant.response.Response;
import com.tom.restaurant.service.VoucherCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Service
public class VoucherCodeServiceImp implements VoucherCodeService {
    @Autowired
    VoucherCodeRepository voucherCodeRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public Response<?> getAll(Pageable pageable) {
        try {
            Page<VoucherCode> listVoucherCode = voucherCodeRepository.findAll(pageable);
            return Response.SUCCESS(listVoucherCode);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.FAIL();
        }
    }

    @Override
    public Response<?> save(VoucherCodeDto voucherCodeDto) {
        try {
            voucherCodeRepository.save(VoucherCode
                    .builder()
                    .id(voucherCodeDto.getId())
                    .code(voucherCodeDto.getId() == null ? generateRandomCode() : voucherCodeDto.getCode())
                    .name(voucherCodeDto.getName())
                    .value(voucherCodeDto.getValue())
                    .userCreateId(voucherCodeDto.getUserCreateId())
                    .status(voucherCodeDto.getStatus())
                    .voucherStartDate(voucherCodeDto.getVoucherStartDate())
                    .voucherExpirationDate(voucherCodeDto.getVoucherExpirationDate())
                    .createDate(new Date())
                    .modifiedDate(new Date())
                    .customers(customerRepository.findByListId(voucherCodeDto.getListCustomerId()))
                    .products(productRepository.findByListId(voucherCodeDto.getListProductId()))
                    .build());
            return Response.SUCCESS();
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
