package com.hanghea99.minispring.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

//**
@Getter
@NoArgsConstructor
public class TokenRequestDto {
	private String accessToken;
	private String refreshToken;
}
