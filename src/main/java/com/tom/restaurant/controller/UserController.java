package com.tom.restaurant.controller;


import com.tom.restaurant.entity.dto.FormChangePassword;
import com.tom.restaurant.entity.dto.FormRegister;
import com.tom.restaurant.entity.dto.FormLogin;
import com.tom.restaurant.entity.dto.UserDto;
import com.tom.restaurant.response.Response;
import com.tom.restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/getAll")
    public Response<?> getAll(@RequestParam int page,
                              @RequestParam int size) {
        return userService.getAll(PageRequest.of(page - 1, size));
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
    public Response<?> edit(@RequestBody UserDto userDto) {
        return userService.editUser(userDto);
    }

    @PostMapping("/changePassword")
    public Response<?> changePassword(@RequestBody FormChangePassword formChangePassword) {
        return userService.changePassword(formChangePassword);
    }
}
