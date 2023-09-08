package com.packt.cardatabase.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.packt.cardatabase.domain.User;
import com.packt.cardatabase.domain.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		
		Optional<User> user = repository.findByUsername(username); 
		//DB에 접근하여 아이디와 비밀번호를 확인하고 가져옴
		UserBuilder builder = null;
		if(user.isPresent()) {	//user라는 객체에 값이 존재하는지 안하는지 체크해줌
			
			User currentUser = user.get();
			builder = 
					org.springframework.security.core.userdetails
					.User.withUsername(username);
			builder.password(currentUser.getPassword());
			builder.roles(currentUser.getRole());
		}else {
			//user 객체에 값이 업으면 not found 에러 발생
			throw new UsernameNotFoundException("User not found.");
		}
		return builder.build();
	}	
}
