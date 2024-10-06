package com.blog.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.entity.Blog;

@Repository
public interface BlogDao extends JpaRepository<Blog, Integer>{
//	List<Blog> findByUser_UserName(String username);
	List<Blog> findByUser_UserNameOrderByCreatedDateDesc(String username);
	

	List<Blog> findAllByOrderByCreatedDateDesc();
}
