package com.canesblack.spring_project1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.canesblack.spring_project1.entity.CustomUser;
import com.canesblack.spring_project1.entity.User;
import com.canesblack.spring_project1.mapper.UserMapper;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		/* String username은 로그인 할 때 사용자가 입력한 아이디다. */
		User user = userMapper.findByUsername(username);
		if(user == null) {
			// 회원 정보가 없는 경우
			throw new UsernameNotFoundException(username + "존재하지 않습니다.");
		}
		// 회원정보가 있는 경우
		
		return new CustomUser(user);
	}
	
	
	
}
