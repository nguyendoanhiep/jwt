package com.tom.restaurant.controller;


import com.tom.restaurant.entity.dto.FormChangePassword;
import com.tom.restaurant.entity.dto.FormRegister;
import com.tom.restaurant.entity.dto.FormLogin;
import com.tom.restaurant.entity.dto.UserRequest;
import com.tom.restaurant.response.Response;
import com.tom.restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response<?> getAll(@RequestParam int page,
                              @RequestParam int size,
                              @RequestParam String search,
                              @RequestParam Integer status
    ) {
        return userService.getAll(PageRequest.of(page - 1, size), search.equals("") ? null : search, status);
    }

    @PostMapping("/register")
    public Response<?> register(@RequestBody FormRegister formRegister) {
        return userService.register(formRegister);
    }

    @PostMapping("/login")
    public Response<?> login(@RequestBody FormLogin formLogin) {
        return userService.login(formLogin);
    }

    @PostMapping("/edit")
    public Response<?> edit(@RequestBody UserRequest request) {
        return userService.editUser(request);
    }

    @PostMapping("/changePassword")
    public Response<?> changePassword(@RequestBody FormChangePassword formChangePassword) {
        return userService.changePassword(formChangePassword);
    }
}
