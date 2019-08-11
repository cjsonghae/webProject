package com.demo.mapper;

import com.demo.vo.Member;

public interface LoginMapper {

	Member checkLogin(String email, String password);

}
