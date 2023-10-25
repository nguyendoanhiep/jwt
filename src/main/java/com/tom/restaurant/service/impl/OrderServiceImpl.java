package com.tom.restaurant.service.impl;

import com.tom.restaurant.entity.Customer;
import com.tom.restaurant.entity.Orders;
import com.tom.restaurant.entity.Voucher;
import com.tom.restaurant.entity.dto.OrdersDto;
import com.tom.restaurant.repository.*;
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
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    VoucherRepository voucherRepository;
    @Autowired
    OrdersProductRepository ordersProductRepository;

    @Override
    public Response<?> getAll(Pageable pageable,String search , Integer status) {
        try {
            Page<OrdersDto> ordersList = orderRepository.getAll(pageable,search,status);
            return Response.SUCCESS(ordersList);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.FAIL(false);
        }
    }

    @Override
    public Response<?> save(OrdersDto ordersDto) {
        try {
            if (ordersDto.getNumberPhone() != null) {
                Customer customer = customerRepository.findByNumberPhone(ordersDto.getNumberPhone());
                if (customer == null) {
                    customerRepository.save(Customer
                            .builder()
                            .id(null)
                            .numberPhone(ordersDto.getNumberPhone())
                            .status(1)
                            .loyaltyPoints(0L)
                            .createDate(new Date())
                            .modifiedDate(new Date())
                            .build());
                } else {
                    customer.setLoyaltyPoints(customer.getLoyaltyPoints() + (ordersDto.getTotalValue()) / 100);
                    customerRepository.save(customer);
                }
            }
            if(ordersDto.getVoucherId() != null){
                Optional<Voucher> voucher = voucherRepository.findById( ordersDto.getVoucherId());
                voucher.ifPresent(entity -> {
                    entity.setStatus(2);
                    voucherRepository.save(entity);
                });
            }
            Orders orders = orderRepository.save(Orders
                    .builder()
                    .id(ordersDto.getId())
                    .code(ordersDto.getId() == null ? generateRandomCode() : ordersDto.getCode())
                    .numberPhone(ordersDto.getNumberPhone())
                    .voucherId(ordersDto.getVoucherId())
                    .originalTotalValue(ordersDto.getOriginalTotalValue())
                    .discountAmount(ordersDto.getDiscountAmount() == null ? 0 : ordersDto.getDiscountAmount())
                    .totalValue(ordersDto.getTotalValue())
                    .status(1)
                    .createDate(ordersDto.getCreateDate() == null ? new Date() : ordersDto.getCreateDate())
                    .modifiedDate(new Date())
                    .build());
            ordersDto.getOrdersProducts().forEach(product -> product.setOrdersId(orders.getId()));
            ordersProductRepository.saveAll(ordersDto.getOrdersProducts());
            return Response.SUCCESS(orders.getId());
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
