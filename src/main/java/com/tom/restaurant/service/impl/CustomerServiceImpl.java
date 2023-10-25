package com.tom.restaurant.service.impl;

import com.tom.restaurant.entity.Customer;
import com.tom.restaurant.entity.dto.CustomerDto;
import com.tom.restaurant.repository.CustomerRepository;
import com.tom.restaurant.response.Response;
import com.tom.restaurant.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

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
    public Response<?> addOrUpdate(CustomerDto dto) {
        try {
            Customer customer = customerRepository.save(Customer
                    .builder()
                    .id(dto.getId())
                    .name(dto.getName())
                    .numberPhone(dto.getNumberPhone())
                    .email(dto.getEmail())
                    .address(dto.getAddress())
                    .imageId(dto.getImageId())
                    .status(dto.getStatus())
                    .dateOfBirth(dto.getDateOfBirth())
                    .createDate(dto.getCreateDate() == null ? new Date() : dto.getCreateDate())
                    .modifiedDate(new Date())
                    .build());
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
