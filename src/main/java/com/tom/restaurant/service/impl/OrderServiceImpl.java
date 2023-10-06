package com.tom.restaurant.service.impl;

import com.tom.restaurant.entity.Customer;
import com.tom.restaurant.entity.Orders;
import com.tom.restaurant.entity.VoucherCode;
import com.tom.restaurant.entity.dto.OrdersDto;
import com.tom.restaurant.repository.CustomerRepository;
import com.tom.restaurant.repository.OrderRepository;
import com.tom.restaurant.repository.ProductRepository;
import com.tom.restaurant.repository.VoucherCodeRepository;
import com.tom.restaurant.response.Response;
import com.tom.restaurant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    VoucherCodeRepository voucherCodeRepository;

    @Override
    public Response<?> getAll(Pageable pageable) {
        try {
            Page<Orders> ordersList = orderRepository.findAll(pageable);
            return Response.SUCCESS(ordersList);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.FAIL();
        }
    }

    @Override
    public Response<?> save(OrdersDto ordersDto) {
        try {
            Customer customer = customerRepository.findByNumberPhone(ordersDto.getNumberPhone());
            if (customer == null) {
                customerRepository.save(Customer
                        .builder()
                        .numberPhone(ordersDto.getNumberPhone())
                        .status(1)
                        .loyaltyPoints(0L)
                        .modifiedDate(new Date())
                        .build());
            } else {
                customer.setLoyaltyPoints((customer.getLoyaltyPoints() + ordersDto.getFinalPrice()) / 100);
                customerRepository.save(customer);
            }
            Optional<VoucherCode> voucherCode = voucherCodeRepository.findById(ordersDto.getVoucherCodeId());
            voucherCode.ifPresent(code -> {
                code.setStatus(0);
                voucherCodeRepository.save(code);
            });
            orderRepository.save(Orders
                    .builder()
                    .id(ordersDto.getId())
                    .code(ordersDto.getId() == null ? generateRandomCode() : ordersDto.getCode())
                    .voucherCodeId(ordersDto.getVoucherCodeId())
                    .finalPrice(ordersDto.getFinalPrice())
                    .discountAmount(ordersDto.getDiscountAmount())
                    .originalPrice(ordersDto.getOriginalPrice())
                    .modifiedDate(new Date())
                    .products(productRepository.findByListId(ordersDto.getListProductId()))
                    .voucherCode(voucherCode.get())
                    .build());
            return Response.SUCCESS();
        } catch (
                Exception e) {
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
