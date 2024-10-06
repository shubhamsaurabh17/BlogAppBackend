package com.blog.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.blog.entity.User;

@Repository
public interface UserDao extends CrudRepository<User, String> {
	
	boolean existsByUserName(String userName);
}
