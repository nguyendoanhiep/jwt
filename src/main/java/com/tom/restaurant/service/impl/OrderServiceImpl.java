package com.tom.restaurant.service.impl;

import com.tom.restaurant.entity.Customer;
import com.tom.restaurant.entity.Orders;
import com.tom.restaurant.entity.Voucher;
import com.tom.restaurant.entity.dto.OrdersRequest;
import com.tom.restaurant.entity.dto.OrdersResponse;
import com.tom.restaurant.repository.*;
import com.tom.restaurant.response.Response;
import com.tom.restaurant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    public Response<?> getAll(Pageable pageable, String search, Integer status) {
        try {
            Page<OrdersResponse> ordersList = orderRepository.getAll(pageable, search, status);
            return Response.SUCCESS(ordersList);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.FAIL(false);
        }
    }

    @Override
    @Transactional
    public Response<?> save(OrdersRequest request) {
        try {
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(()->{
                System.out.println("ok");
            });
            executorService.execute(()->{
                System.out.println("ok");
            });
            if (request.getNumberPhone() != null) {
                Customer customer = customerRepository.findByNumberPhone(request.getNumberPhone());
                if (customer == null) {
                    customerRepository.save(Customer
                            .builder()
                            .id(null)
                            .numberPhone(request.getNumberPhone())
                            .status(1)
                            .loyaltyPoints(0L)
                            .createDate(new Date())
                            .modifiedDate(new Date())
                            .build());
                } else {
                    customer.setLoyaltyPoints(customer.getLoyaltyPoints() + (request.getTotalValue()) / 100);
                    customerRepository.save(customer);
                }
            }
            if (request.getVoucherId() != null) {
                Optional<Voucher> voucher = voucherRepository.findById(request.getVoucherId());
                voucher.ifPresent(entity -> {
                    if (entity.getQuantity() > 0) {
                        entity.setQuantity(entity.getQuantity() - 1);
                        if (entity.getQuantity() == 0) {
                            entity.setStatus(2);
                        }
                        voucherRepository.save(entity);
                    }
                });
            }
            Orders orders = orderRepository.save(Orders
                    .builder()
                    .id(request.getId())
                    .code(request.getId() == null ? generateRandomCode() : request.getCode())
                    .numberPhone(request.getNumberPhone())
                    .voucherId(request.getVoucherId())
                    .originalTotalValue(request.getOriginalTotalValue())
                    .discountAmount(request.getDiscountAmount() == null ? 0 : request.getDiscountAmount())
                    .totalValue(request.getTotalValue())
                    .status(1)
                    .createDate(new Date())
                    .modifiedDate(new Date())
                    .build());
            request.getOrdersProducts().forEach(product -> product.setOrdersId(orders.getId()));
            ordersProductRepository.saveAll(request.getOrdersProducts());
            return Response.SUCCESS(true);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.FAIL(false);
        }
    }

    @Override
    public Response<?> activationOfTurnOff(Long id) {
        try {
            Orders orders = orderRepository.findById(id).get();
            if (orders.getStatus() == 1) {
                orders.setStatus(2);
                orderRepository.save(orders);
                return Response.SUCCESS(true);
            }
            if (orders.getStatus() == 2) {
                orders.setStatus(1);
                orderRepository.save(orders);
                return Response.SUCCESS(true);
            }
            return Response.FAIL(false);
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
