package com.windsoft.starter.auth.service.impl;

import com.windsoft.starter.auth.data.entity.User;
import com.windsoft.starter.auth.dto.LoginRequestDto;
import com.windsoft.starter.auth.dto.LoginResponseDto;
import com.windsoft.starter.auth.service.LoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public LoginResponseDto login(LoginRequestDto request) {

        if (!request.getEmail().equals("admin@test.com") || !"1234".equals(request.getPassword())) {
            return null;
        }

        User user = new User(1L, request.getEmail(), "������");

        return new LoginResponseDto(user);
    }

    @Override
    public LoginResponseDto findUser(Long userId) {

        if (userId < 0) {
            return null;
        }

        User user = new User(userId, "admin@test.com", "������");

        return new LoginResponseDto(user);
    }


}
