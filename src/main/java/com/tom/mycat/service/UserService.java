package com.tom.mycat.service;

import com.tom.mycat.entity.dto.FormChangePassword;
import com.tom.mycat.entity.dto.FormRegister;
import com.tom.mycat.entity.dto.UserDto;
import com.tom.mycat.entity.dto.FormLogin;
import com.tom.mycat.response.Response;

public interface UserService {
    Response<?> register(FormRegister formRegister);
    Response<?> login(FormLogin formLogin);
    Response<?> editUser(UserDto userDto);
    Response<?> changePassword(FormChangePassword formChangePassword);
}
