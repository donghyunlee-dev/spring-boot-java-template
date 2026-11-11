package com.windsoft.starter.auth.service;

import com.windsoft.starter.auth.dto.LoginRequestDto;
import com.windsoft.starter.auth.dto.LoginResponseDto;

public interface LoginService {
    LoginResponseDto login(LoginRequestDto request);

    LoginResponseDto findUser(Long userId);
}
