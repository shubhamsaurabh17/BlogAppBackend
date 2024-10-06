package com.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.CommentRepo;
import com.blog.entity.Comment;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepo commentRepo;
	
	public Comment addComment(Comment comment) {
		return this.commentRepo.save(comment);
	}

}
