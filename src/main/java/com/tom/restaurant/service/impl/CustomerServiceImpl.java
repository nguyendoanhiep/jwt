package com.tom.restaurant.service.impl;

import com.tom.restaurant.entity.Customer;
import com.tom.restaurant.entity.dto.CustomerRequest;
import com.tom.restaurant.entity.dto.CustomerResponse;
import com.tom.restaurant.repository.*;
import com.tom.restaurant.response.Response;
import com.tom.restaurant.service.CustomerService;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
    @Autowired
    EntityManager entityManager;

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
//            StringBuilder sql = new StringBuilder(
//                    "SELECT c.id, c.name, c.number_phone, c.status, c.loyalty_points, EXISTS (SELECT 1 FROM voucher_customer vc " +
//                            "WHERE vc.number_phone = c.number_phone AND vc.voucher_id = :voucherId) AS result " +
//                            "FROM customer c WHERE c.status = 1 ");
//            if(search != null){
//                sql.append("and (c.name LIKE :search OR c.number_phone LIKE :search)");
//            }
//            Query query = entityManager.createNativeQuery(sql.toString(),CustomerResponse.class);
//            query.setParameter("voucherId", voucherId);
//            if(search != null){
//                query.setParameter("search", search + "%");
//            }
//            List<CustomerResponse> res = query.getResultList();
            List<CustomerResponse> res = customerRepository.getAllByVoucherId(pageable,search, voucherId);
            return Response.SUCCESS(res);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.FAIL(false);
        }
    }

    @Override
    @Transactional
    public Response<?> addOrUpdate(CustomerRequest request) {
        try {
            Customer customer = request.getId() == null ? new Customer() : customerRepository.findById(request.getId()).get(); ;
            if (request.getId() != null) {
                userRepository.updateNumberPhone(customer.getNumberPhone(),request.getNumberPhone());
                orderRepository.updateNumberPhone(customer.getNumberPhone(),request.getNumberPhone());
                voucherCustomerRepository.updateNumberPhone(customer.getNumberPhone(),request.getNumberPhone());
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
            if(request.getId() == null){
                customer.setCreateDate(new Date());
            }
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
