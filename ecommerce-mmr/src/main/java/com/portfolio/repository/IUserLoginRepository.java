package com.portfolio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.model.UserLogin;

@Repository
public interface IUserLoginRepository extends JpaRepository<UserLogin, Integer> {
	Optional<UserLogin> findByEmail(String email);
}
