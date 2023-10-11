package com.tom.restaurant.service.impl;

import com.tom.restaurant.entity.Cart;
import com.tom.restaurant.entity.Product;
import com.tom.restaurant.entity.Voucher;
import com.tom.restaurant.repository.CartRepository;
import com.tom.restaurant.repository.ProductRepository;
import com.tom.restaurant.repository.VoucherRepository;
import com.tom.restaurant.response.Response;
import com.tom.restaurant.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    VoucherRepository voucherRepository;

    @Override
    public Response<?> showCart(Long customerId, Long voucherId) {
        try {
            return Response.SUCCESS(cartRepository.showCart(customerId, voucherId));
        } catch (Exception e) {
            return Response.FAIL();
        }
    }

    @Override
    public Response<?> addProductForCart(Long customerId, Long productId) {
        try {
            Cart cart = cartRepository.findByCustomerId(customerId);
            Product product = productRepository.findById(productId).orElse(null);
            cart.getProducts().add(product);
            calculateOrder(cart);
            cartRepository.save(cart);
            return Response.SUCCESS(cart);
        } catch (Exception e) {
            return Response.FAIL();
        }
    }

    @Override
    public Response<?> deleteProductForCart(Long customerId, Long productId) {
        try {
            Cart cart = cartRepository.findByCustomerId(customerId);
            Product product = productRepository.findById(productId).orElse(null);
            cart.getProducts().removeIf(data -> data.equals(product));
            calculateOrder(cart);
            cartRepository.save(cart);
            return Response.SUCCESS(cart);
        } catch (Exception e) {
            return Response.FAIL();
        }
    }

    @Override
    public Response<?> addOrDeleteVoucher(Long customerId, Long voucherId) {
        try {
            Cart cart = cartRepository.findByCustomerId(customerId);
            Voucher voucher = voucherRepository.findById(voucherId).get();
            if (cart.getVoucher() != null && cart.getVoucher().equals(voucher)) {
                cart.setVoucherId(null);
                cart.setVoucher(null);
                cart.setDiscountAmount(0L);
            } else {
                cart.setVoucherId(voucherId);
                cart.setVoucher(voucher);
            }
            calculateOrder(cart);
            cartRepository.save(cart);
            return Response.SUCCESS(cart);
        } catch (Exception e) {
            return Response.FAIL();
        }
    }

    private void calculateOrder(Cart cart) {
        if (cart.getVoucher() != null && cart.getVoucher().getValue() != null) {
            cart.setDiscountAmount(cart.getVoucher().getValue());
        }
        if (!cart.getProducts().isEmpty()) {
            cart.setOriginalPrice(cart.getProducts().stream().mapToLong(Product::getPrice).sum());
        }
        cart.setFinalPrice(cart.getOriginalPrice() - cart.getDiscountAmount());
    }
}
