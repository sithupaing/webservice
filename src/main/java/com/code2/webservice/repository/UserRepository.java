package com.code2.webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code2.webservice.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
