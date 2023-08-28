package com.sesac.reuse.repository;

import com.sesac.reuse.domain.UserDomain;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserDomain, String> {

    Optional<UserDomain> findByEmailAndOauthType(String email, String oauthType);
}
