package com.hanghea99.minispring.controller;


import com.hanghea99.minispring.model.dto.MemberRequestDto;
import com.hanghea99.minispring.model.dto.MemberResponseDto;
import com.hanghea99.minispring.model.dto.TokenDto;
import com.hanghea99.minispring.model.dto.TokenRequestDto;
import com.hanghea99.minispring.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//**
@RestController
@RequestMapping("/sign")
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;

	@PostMapping("/signup")
	public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto memberRequestDto) {
		return ResponseEntity.ok(authService.signup(memberRequestDto));
	}

	@PostMapping("/singin")
	public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto memberRequestDto) {
		return ResponseEntity.ok(authService.login(memberRequestDto));
	}

	@PostMapping("/reissue")
	public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
		return ResponseEntity.ok(authService.reissue(tokenRequestDto));
	}
}
