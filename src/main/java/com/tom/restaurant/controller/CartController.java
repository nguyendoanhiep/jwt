package com.tom.restaurant.controller;

import com.tom.restaurant.response.Response;
import com.tom.restaurant.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @GetMapping("/showCart")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Response<?> showCart(@RequestParam Long customerId, @RequestParam Long productId) {
        return cartService.showCart(customerId, productId);
    }
    @GetMapping("/addProductForCart")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Response<?> addProductForCart(@RequestParam Long customerId, @RequestParam Long productId) {
        return cartService.addProductForCart(customerId, productId);
    }
    @GetMapping("/deleteProductForCart")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Response<?> deleteProductForCart(@RequestParam Long customerId, @RequestParam Long productId) {
        return cartService.deleteProductForCart(customerId, productId);
    }

    @GetMapping("/addOrDeleteVoucher")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Response<?> addOrDeleteVoucher(@RequestParam Long customerId, @RequestParam Long voucherId) {
        return cartService.addOrDeleteVoucher(customerId, voucherId);
    }
}
