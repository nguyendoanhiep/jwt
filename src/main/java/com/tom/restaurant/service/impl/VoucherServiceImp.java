package com.tom.restaurant.service.impl;

import com.tom.restaurant.entity.Voucher;
import com.tom.restaurant.entity.VoucherCustomer;
import com.tom.restaurant.entity.dto.VoucherDto;
import com.tom.restaurant.repository.VoucherCustomerRepository;
import com.tom.restaurant.repository.VoucherRepository;
import com.tom.restaurant.response.Response;
import com.tom.restaurant.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class VoucherServiceImp implements VoucherService {
    @Autowired
    VoucherRepository voucherRepository;
    @Autowired
    VoucherCustomerRepository voucherCustomerRepository;

    @Override
    public Response<?> getAll(Pageable pageable, String search, Integer status) {
        try {
            Page<Voucher> listVoucher = voucherRepository.getAll(pageable, search,status);
            return Response.SUCCESS(listVoucher);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.FAIL(false);
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
            return Response.FAIL(false);
        }
    }

    @Override
    public Response<?> findByNumberPhone(String numberPhone) {
        try {
            List<Voucher> listVoucher = voucherRepository.findByNumberPhone(numberPhone);
            return Response.SUCCESS(listVoucher);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.FAIL(false);
        }
    }

    @Override
    public Response<?> addVoucherForCustomer(List<String> numberPhoneList, Long voucherId) {
        try {
            List<VoucherCustomer> voucherCustomerList = numberPhoneList.stream().map(numberPhone -> {
                VoucherCustomer voucherCustomer = new VoucherCustomer();
                voucherCustomer.setVoucherId(voucherId);
                voucherCustomer.setNumberPhone(numberPhone);
                return voucherCustomer;
            }).collect(Collectors.toList());
            voucherCustomerRepository.saveAll(voucherCustomerList);
            return Response.SUCCESS(true);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.FAIL(false);
        }
    }

    @Override
    public Response<?> delete(Long id) {
        try {
            voucherRepository.deleteById(id);
            return Response.SUCCESS(true);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.FAIL(false);
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
