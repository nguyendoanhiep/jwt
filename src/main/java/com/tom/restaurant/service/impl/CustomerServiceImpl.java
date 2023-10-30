package com.tom.restaurant.service.impl;

import com.tom.restaurant.entity.Customer;
import com.tom.restaurant.entity.dto.CustomerRequest;
import com.tom.restaurant.entity.dto.CustomerResponse;
import com.tom.restaurant.repository.*;
import com.tom.restaurant.response.Response;
import com.tom.restaurant.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

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
    public Response<?> findById(Long id) {
        return Response.SUCCESS(customerRepository.findById(id).get());
    }

    @Override
    public Response<?> getAllByVoucherId(Pageable pageable, String search, Long voucherId) {
        try {
            Page<CustomerResponse> res1 = customerRepository.getAllByVoucherIdExits(pageable, search, voucherId);
            Page<CustomerResponse> res2 = customerRepository.getAllByVoucherIdNotExits(pageable, search);
            Set<CustomerResponse> totalRes = new HashSet<>();
            totalRes.addAll(res1.getContent());
            totalRes.addAll(res2.getContent());
            return Response.SUCCESS(totalRes.stream().sorted(Comparator.comparing(CustomerResponse::getId).reversed()).collect(Collectors.toList()));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.FAIL(false);
        }
    }

    @Override
    @Transactional
    public Response<?> addOrUpdate(CustomerRequest request) {
        try {
            Customer customer = new Customer();
            if (request.getId() != null) {
                Customer cus = customerRepository.findById(request.getId()).get();
                userRepository.updateNumberPhone(cus.getNumberPhone(),request.getNumberPhone());
                orderRepository.updateNumberPhone(cus.getNumberPhone(),request.getNumberPhone());
                voucherCustomerRepository.updateNumberPhone(cus.getNumberPhone(),request.getNumberPhone());
            }
            customer.setId(request.getId());
            customer.setName(request.getName());
            customer.setNumberPhone(request.getNumberPhone());
            customer.setEmail(request.getEmail());
            customer.setAddress(request.getAddress());
            customer.setUrlImage(request.getUrlImage());
            customer.setStatus(request.getStatus());
            customer.setLoyaltyPoints(request.getLoyaltyPoints()== null ? 0 : request.getLoyaltyPoints());
            customer.setDateOfBirth(request.getDateOfBirth());
            customer.setStatus(request.getStatus());
            customer.setCreateDate(request.getCreateDate() == null ? new Date() : request.getCreateDate());
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
