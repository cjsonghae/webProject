package com.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.mapper.LoginMapper;
import com.demo.service.JwtLoginService;
import com.demo.vo.Member;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class LoginController {

	@Autowired(required=true)
	LoginMapper loginMapper;
	@Autowired(required=true)
	JwtLoginService jwtLoginService;
	
	@RequestMapping(value="/login", method=RequestMethod.POST, produces = { "application/json;charset=euc-kr" })
	public Map login(@RequestBody Member member) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			Member memberInfo = loginMapper.checkLogin(member.getEmail(), member.getPassword());
			String token = jwtLoginService.createToken(member);
			System.out.println(token);
			map.put("token", token);
			map.put("success", "true");
			String success = jwtLoginService.verifyToken(token);
			System.out.println("검증시작전");
			if(success.equals("true")) {
				map.put("verify" , "true");
			}else {
				map.put("verify", "false");
			}
			
		} catch (Exception e) {
			map.put("success", "false");
		}
		return map;
		
	}
}
