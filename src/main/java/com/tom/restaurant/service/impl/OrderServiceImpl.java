package com.tom.restaurant.service.impl;

import com.tom.restaurant.entity.Customer;
import com.tom.restaurant.entity.Orders;
import com.tom.restaurant.entity.dto.OrdersDto;
import com.tom.restaurant.repository.CustomerRepository;
import com.tom.restaurant.repository.OrderRepository;
import com.tom.restaurant.response.Response;
import com.tom.restaurant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderRepository orderRepository;

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
            if (customer != null) {
                return Response.FAIL();
            }
            if (ordersDto.getId() != null) {
                customerRepository.save(Customer
                        .builder()
                        .numberPhone(ordersDto.getNumberPhone())
                        .status(1)
                        .loyaltyPoints(0L)
                        .createDate(new Date())
                        .modifiedDate(new Date())
                        .build());
            }
            orderRepository.save(Orders
                    .builder()
                    .id(ordersDto.getId())
                    .code(ordersDto.getCode())
                    .customerId(ordersDto.getCustomerId())
                    .finalPrice(ordersDto.getFinalPrice())
                    .discountAmount(ordersDto.getDiscountAmount())
                    .originalPrice(ordersDto.getOriginalPrice())
                    .products(ordersDto.getProducts())
                    .voucherCodes(ordersDto.getVoucherCodes())
                    .createDate(ordersDto.getCreateDate())
                    .modifiedDate(new Date())
                    .build());
            return Response.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.FAIL();
        }
    }


}
