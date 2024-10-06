package com.blog.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.entity.Blog;
import com.blog.entity.Comment;
import com.blog.service.BlogService;
import com.blog.service.CommentService;

@RestController
public class CommentController {

	@Autowired
	private CommentService commentService;

	@Autowired
	private BlogService blogService;

	@PostMapping("/comment/{blogID}")
	public Comment addComment(@PathVariable("blogID") int blogID, @RequestBody Comment comment, Principal principal) {
		Blog blogs = this.blogService.getBlogById(blogID);
		comment.setBlog(blogs);
		
		String name = principal.getName();
		comment.setCommentedBy(name);

		Comment commentAdded = this.commentService.addComment(comment);
		return commentAdded;

	}

}
