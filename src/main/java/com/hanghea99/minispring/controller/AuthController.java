package com.hanghea99.minispring.controller;


import com.hanghea99.minispring.model.dto.*;
import com.hanghea99.minispring.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//**
@RestController
@RequestMapping("/api")
@CrossOrigin
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
//		httpServletResponse.setHeader("Authorization", "Bearer    " + tokenDto.getAccessToken());
		Cookie jwt = new Cookie("jwt",  tokenDto.getAccessToken());
		jwt.setMaxAge(1000 * 60 * 60 * 12);
		httpServletResponse.addCookie(jwt);
		return "환영합니다." + memberRequestDto.getUsername() + "님";
	}

	@PostMapping("/logout")
	private String logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
		Cookie jwt = new Cookie("jwt", null);
		jwt.setMaxAge(0);
		httpServletResponse.addCookie(jwt);
		return "로그아웃";
	}

	@PostMapping("/reissue")
	public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
		return ResponseEntity.ok(authService.reissue(tokenRequestDto));
	}
}
