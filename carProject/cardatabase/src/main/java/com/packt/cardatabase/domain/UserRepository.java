package com.packt.cardatabase.domain;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)	// false로 설정하면 리포지터리가 REST 리소스로 노출되지 않는다.
public interface UserRepository extends CrudRepository<User, Long>{
	Optional<User> findByUsername(String username);		//메서드가 null 예이를 방지하기 위해 Optional을 반환
}

//90쪽 JWT전까지 완료  20230901 마무리 