package com.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.BlogDao;
import com.blog.entity.Blog;
import com.blog.exception.BlogException;

@Service
public class BlogService {
	
	@Autowired
	private BlogDao blogDao;
	
	public Blog addBlog(Blog blog) {
		return this.blogDao.save(blog);
	}
	
	
	public Blog getBlogById(int id) throws BlogException {
		return this.blogDao.findById(id).orElseThrow(()-> new BlogException(("Not Found")));
	}
	
	
	public List<Blog> getBlogByUser(String username) {
		
		return this.blogDao.findByUser_UserNameOrderByCreatedDateDesc(username);
	}
	
	public List<Blog> getAllBlog(){
//		return this.blogDao.findAll();
		return this.blogDao.findAllByOrderByCreatedDateDesc();
	}

}
