package com.tom.restaurant.controller;

import com.tom.restaurant.response.Response;
import com.tom.restaurant.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @GetMapping("/showCart")
    public Response<?> showCart(@RequestParam Long customerId, @RequestParam Long productId) {
        return cartService.showCart(customerId, productId);
    }
    @GetMapping("/addProductForCart")
    public Response<?> addProductForCart(@RequestParam Long customerId, @RequestParam Long productId) {
        return cartService.addProductForCart(customerId, productId);
    }
    @GetMapping("/deleteProductForCart")
    public Response<?> deleteProductForCart(@RequestParam Long customerId, @RequestParam Long productId) {
        return cartService.deleteProductForCart(customerId, productId);
    }

    @GetMapping("/addOrDeleteVoucher")
    public Response<?> addOrDeleteVoucher(@RequestParam Long customerId, @RequestParam Long voucherCodeId) {
        return cartService.addOrDeleteVoucher(customerId, voucherCodeId);
    }
}
