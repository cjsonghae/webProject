package com.demo.service;

import com.demo.vo.Member;

public interface JwtLoginService {
	String createToken(Member member);
	public String verifyToken(String token);
}
