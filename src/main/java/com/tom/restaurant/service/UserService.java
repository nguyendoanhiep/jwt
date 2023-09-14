package com.tom.restaurant.service;

import com.tom.restaurant.entity.dto.FormChangePassword;
import com.tom.restaurant.entity.dto.FormRegister;
import com.tom.restaurant.entity.dto.UserDto;
import com.tom.restaurant.entity.dto.FormLogin;
import com.tom.restaurant.response.Response;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Response<?> getAll(Pageable pageable);

    Response<?> register(FormRegister formRegister);
    Response<?> login(FormLogin formLogin);
    Response<?> editUser(UserDto userDto);
    Response<?> changePassword(FormChangePassword formChangePassword);
}
