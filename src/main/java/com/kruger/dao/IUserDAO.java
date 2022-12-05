package com.kruger.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kruger.model.User;

public interface IUserDAO extends JpaRepository<User, Integer> {

	User findOneByUsername(String username);
}