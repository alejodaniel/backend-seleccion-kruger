package com.kruger.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.kruger.model.User;

@Repository
public interface IUserAdministrationDAO extends JpaRepository<User, Integer> {

}
