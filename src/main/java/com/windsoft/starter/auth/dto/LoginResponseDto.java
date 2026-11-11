package com.windsoft.starter.auth.dto;

import com.windsoft.starter.auth.data.entity.User;
import lombok.*;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginResponseDto {
    private User user;
}

