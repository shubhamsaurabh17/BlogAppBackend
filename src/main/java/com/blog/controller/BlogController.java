package com.blog.controller;

import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.dao.UserDao;
import com.blog.entity.Blog;
import com.blog.entity.User;
import com.blog.exception.BlogException;
import com.blog.service.BlogService;
import com.blog.service.FileService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	@Autowired
	private ObjectMapper mapper;

	
//	@PostMapping({"/blog"})
//	public Blog addBlog(@RequestBody Blog blog, Principal princiapl) {
//		String userName = princiapl.getName();
//		
//		User user = this.userDao.findById(userName).orElseThrow(()-> new BlogException("User Not Found"));
//		blog.setUser(user);
//		Blog addBlog = this.blogService.addBlog(blog);
//		return addBlog;
//	}
	
	@PostMapping({"/blog"})
	public Blog addBlog(
			@RequestParam("blogData") String blogData,
			@RequestParam("image") MultipartFile image,
			Principal princiapl) throws IOException {
		String userName = princiapl.getName();
		
		User user = this.userDao.findById(userName).orElseThrow(()-> new BlogException("User Not Found"));
		
		Blog blog = this.mapper.readValue(blogData, Blog.class);
		
		String uploadImage = this.fileService.uploadImage(path, image);
		blog.setImageName(uploadImage);
		
		blog.setUser(user);
		Blog addBlog = this.blogService.addBlog(blog);
		return addBlog;
	}
	
	
//	@PostMapping("/uploadImage/{blogID}")
//	public Blog postImage(@RequestParam("image") MultipartFile image,
//			@PathVariable("blogID") int blogID
//			) throws IOException {
//		String uploadImage = this.fileService.uploadImage(path, image);
//		
//		Blog blogById = this.blogService.getBlogById(blogID);
//		blogById.setImageName(uploadImage);
//		
//		Blog addBlog = this.blogService.addBlog(blogById);
//		return addBlog;
//	}
	
	@GetMapping(value= "/getImage/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void getResource(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
	
	@GetMapping({"/blog/{id}"})
	public Blog getBlogById(@PathVariable("id") int id) {
		Blog blogById = this.blogService.getBlogById(id);
		return blogById;
	}
	
	@GetMapping({"/userBlog"})
	public List<Blog> getBlogByUser(Principal principal) {
		String userName= principal.getName();
		return this.blogService.getBlogByUser(userName);
	}
	
	@GetMapping({"/getAllBlog"})
	public List<Blog> allBlog(){
		return this.blogService.getAllBlog();
	}
}
