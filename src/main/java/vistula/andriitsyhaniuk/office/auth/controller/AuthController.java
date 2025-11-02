package vistula.andriitsyhaniuk.office.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vistula.andriitsyhaniuk.office.auth.dto.LoginRequestDto;
import vistula.andriitsyhaniuk.office.auth.dto.LoginResponseDto;
import vistula.andriitsyhaniuk.office.auth.dto.RefreshTokenDto;
import vistula.andriitsyhaniuk.office.auth.dto.RegisterRequestDto;
import vistula.andriitsyhaniuk.office.auth.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<LoginResponseDto> register(@RequestBody RegisterRequestDto request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/login/access-token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenDto request) {
        return ResponseEntity.ok(authService.refreshToken(request.getRefreshToken()));
    }
}


