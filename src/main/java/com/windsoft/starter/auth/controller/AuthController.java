package com.windsoft.starter.auth.controller;

import com.windsoft.starter.auth.data.entity.User;
import com.windsoft.starter.auth.dto.LoginRequestDto;
import com.windsoft.starter.auth.dto.LoginResponseDto;
import com.windsoft.starter.auth.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthController {

    private static final String USER_KEY = "USER_LOGIN_ID";
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(HttpSession session, @RequestBody LoginRequestDto request) {

        LoginResponseDto res = loginService.login(request);

        if (res == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "�α��� ����");
        }

        session.setAttribute(USER_KEY, res.getUser().getId());

        log.debug(res.toString());

        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<LoginResponseDto> getMe(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        Long userId = (Long) session.getAttribute(USER_KEY);

        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        LoginResponseDto res = loginService.findUser(userId);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logOut(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return (ResponseEntity<Void>) ResponseEntity.noContent();
    }
}
