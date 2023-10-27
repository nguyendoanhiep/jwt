package com.tom.restaurant.service.impl;

import com.tom.restaurant.entity.Customer;
import com.tom.restaurant.entity.Orders;
import com.tom.restaurant.entity.User;
import com.tom.restaurant.entity.dto.CustomerDto;
import com.tom.restaurant.repository.*;
import com.tom.restaurant.response.Response;
import com.tom.restaurant.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    VoucherCustomerRepository voucherCustomerRepository;

    @Override
    public Response<?> getAll(Pageable pageable, String search, Integer status) {
        try {
            return Response.SUCCESS(customerRepository.getAll(pageable, search, status));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.FAIL(false);
        }
    }

    @Override
    @Transactional
    public Response<?> addOrUpdate(CustomerDto dto) {
        try {
            Customer customer = new Customer();
            if (dto.getId() != null) {
                Customer cus = customerRepository.findById(dto.getId()).get();
                userRepository.updateNumberPhone(cus.getNumberPhone(),dto.getNumberPhone());
                orderRepository.updateNumberPhone(cus.getNumberPhone(),dto.getNumberPhone());
                voucherCustomerRepository.updateNumberPhone(cus.getNumberPhone(),dto.getNumberPhone());
            }
            customer.setId(dto.getId());
            customer.setName(dto.getName());
            customer.setNumberPhone(dto.getNumberPhone());
            customer.setEmail(dto.getEmail());
            customer.setAddress(dto.getAddress());
            customer.setUrlImage(dto.getUrlImage());
            customer.setStatus(dto.getStatus());
            customer.setLoyaltyPoints(dto.getLoyaltyPoints()== null ? 0 : dto.getLoyaltyPoints());
            customer.setDateOfBirth(dto.getDateOfBirth());
            customer.setStatus(dto.getStatus());
            customer.setCreateDate(dto.getCreateDate() == null ? new Date() : dto.getCreateDate());
            customer.setModifiedDate(new Date());
            customerRepository.save(customer);
            return Response.SUCCESS(true);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.FAIL(false);
        }
    }

    @Override
    public Response<?> delete(Long id) {
        try {
            customerRepository.deleteById(id);
            return Response.SUCCESS(true);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.FAIL(false);
        }
    }

}
