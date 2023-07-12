package com.tom.mycat.service.impl;

import com.tom.mycat.entity.Role;
import com.tom.mycat.entity.RoleType;
import com.tom.mycat.entity.User;
import com.tom.mycat.entity.dto.FormChangePassword;
import com.tom.mycat.entity.dto.FormRegister;
import com.tom.mycat.entity.dto.UserDto;
import com.tom.mycat.entity.dto.FormLogin;
import com.tom.mycat.jwt.JwtAuthenticationFilter;
import com.tom.mycat.jwt.JwtTokenProvider;
import com.tom.mycat.repository.RoleRepository;
import com.tom.mycat.repository.UserRepository;
import com.tom.mycat.response.Response;
import com.tom.mycat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    public Response<?> getAll(Pageable pageable) {
        return new Response<>(200, "Success", userRepository.findAll(pageable));
    }

    @Override
    public Response<?> register(FormRegister formRegister) {
        try {
            Optional<User> checkUserExists = userRepository.findByUsername(formRegister.getUsername());
            if (checkUserExists.isPresent()) {
                return new Response<>(410, "Data Already Exists", null);
            }
            Set<Role> roles = new HashSet<>();
            formRegister.getRoles().forEach(role -> {
                switch (role) {
                    case "user":
                        Role userRole = roleRepository.findByName(RoleType.ROLE_USER).orElseThrow(() -> new RuntimeException("Role not found"));
                        roles.add(userRole);
                    case "admin":
                        Role adminRole = roleRepository.findByName(RoleType.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Role not found"));
                        roles.add(adminRole);
                }
            });
            User user = User.builder()
                    .username(formRegister.getUsername())
                    .password(passwordEncoder.encode(formRegister.getPassword()))
                    .numberPhone(formRegister.getNumberPhone())
                    .name(formRegister.getFullName())
                    .roles(roles)
                    .status(1)
                    .createDate(new Date())
                    .modifiedDate(new Date())
                    .build();
            userRepository.save(user);
            return new Response<>(200, "Success", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(400, "Fail", null);
        }
    }

    @Override
    public Response<?> login(FormLogin formLogin) {
        try {
            Optional<User> user = userRepository.findByUsername(formLogin.getUsername());
            if (!user.isPresent()) {
                return new Response<>(420, "ACCOUNT NOT FOUND", null);
            }
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            formLogin.getUsername(),
                            formLogin.getPassword()
                    ));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtTokenProvider.generateToken((User) authentication.getPrincipal());
            return new Response<>(200, "Success", jwt);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(400, "Fail", null);
        }
    }

    @Override
    public Response<?> editUser(UserDto userDto) {
        try {
            User user = User.builder()
                    .id(userDto.getId())
                    .name(userDto.getName())
                    .username(userDto.getUsername())
                    .password(userDto.getPassword())
                    .email(userDto.getEmail())
                    .image_id(userDto.getImage_id())
                    .age(userDto.getAge())
                    .gender(userDto.getGender())
                    .city(userDto.getCity())
                    .status(userDto.getStatus())
                    .build();
            userRepository.save(user);
            return new Response<>(200, "Success", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(400, "Fail", null);
        }

    }

    @Override
    public Response<?> changePassword(FormChangePassword formChangePassword) {
        try {
            User user = userRepository.findById(formChangePassword.getId()).get();
            boolean isPasswordMatch = passwordEncoder.matches(formChangePassword.getCurrentPassword(), user.getPassword());
            if(!isPasswordMatch){
                return new Response<>(430, "Current Password Is Not Correct", null);
            }
            user.setPassword(passwordEncoder.encode(formChangePassword.getNewPassword()));
            userRepository.save(user);
            return new Response<>(200, "Success", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(400, "Fail", null);
        }
    }
}
