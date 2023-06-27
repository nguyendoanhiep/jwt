package com.tom.mycat.controller;


import com.tom.mycat.entity.dto.FormChangePassword;
import com.tom.mycat.entity.dto.FormRegister;
import com.tom.mycat.entity.dto.FormLogin;
import com.tom.mycat.entity.dto.UserDto;
import com.tom.mycat.response.Response;
import com.tom.mycat.service.UserService;
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
