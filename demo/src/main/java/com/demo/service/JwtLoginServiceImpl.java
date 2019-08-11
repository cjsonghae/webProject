package com.demo.service;


import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.demo.vo.Member;

@Service
public class JwtLoginServiceImpl implements JwtLoginService{

	@Override
	public String createToken(Member member) {
		String token = null;
		HashMap<String, String> claims = new HashMap<String, String>();
		claims.put("id" , member.getEmail());
		try {
			Algorithm algorithm = Algorithm.HMAC256("secret");
			 token = JWT.create()
					.withClaim("id", member.getEmail())
					.sign(algorithm);
		} catch (Exception e) {
			System.out.println("오류");
		}
		return token;
		
	}
	
	public String verifyToken(String token) {
		System.out.println(token);
		System.out.println("검증시작");
		try {
		    Algorithm algorithm = Algorithm.HMAC256("secret");
		    JWTVerifier verifier = JWT.require(algorithm)
		        .withIssuer("auth0")
		        .build(); //Reusable verifier instance
		    DecodedJWT jwt = verifier.verify(token);
		    System.out.println(jwt);
		    return "true";
		} catch (JWTVerificationException exception){
		    //Invalid signature/claims
			return "false";
					
		}
	}
	
}
