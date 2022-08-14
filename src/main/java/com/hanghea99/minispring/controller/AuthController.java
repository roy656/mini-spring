package com.hanghea99.minispring.controller;


import com.hanghea99.minispring.model.dto.*;
import com.hanghea99.minispring.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

//**
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;

	@PostMapping("/signup")
	public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto memberRequestDto) {
		return ResponseEntity.ok(authService.signup(memberRequestDto));
	}

	@PostMapping("/signup/check")
	public Boolean check(@RequestBody UsernameDto usernameDto){
		return authService.check(usernameDto);
	}


	@PostMapping("/login")
	public String login(@RequestBody MemberRequestDto memberRequestDto, HttpServletResponse httpServletResponse) {
		TokenDto tokenDto = authService.login(memberRequestDto);
		httpServletResponse.setHeader("Authorization", "Bearer    " + tokenDto.getAccessToken());
		return "환영합니다." + memberRequestDto.getUsername() + "님";
	}

	@PostMapping("/reissue")
	public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
		return ResponseEntity.ok(authService.reissue(tokenRequestDto));
	}
}
