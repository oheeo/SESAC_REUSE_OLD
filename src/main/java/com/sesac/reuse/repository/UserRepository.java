package com.sesac.reuse.repository;

import com.sesac.reuse.domain.UserDomain;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// UserDomain을 DB에 저장하기 위한 리포지토리
@Repository
public interface UserRepository extends CrudRepository<UserDomain, String> {

    Optional<UserDomain> findByEmailAndOauthType(String email, String oauthType);
}
