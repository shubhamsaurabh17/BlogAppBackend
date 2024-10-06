package com.blog.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Blog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	@Column(length = 10000)
	private String content;
	private String category;
	private Date createdDate=new Date();
	private String imageName;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnoreProperties("blogs")
	private User user;

	// One blog can have many comments
	@OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
	@JsonManagedReference
	@OrderBy("commentedOn DESC")
	private List<Comment> comments;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Blog [id=" + id + ", title=" + title + ", content=" + content + ", category=" + category
				+ ", createdDate=" + createdDate + ", imageName=" + imageName + ", user=" + user + ", comments="
				+ comments + "]";
	}

	public Blog(int id, String title, String content, String category, Date createdDate, String imageName, User user,
			List<Comment> comments) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.category = category;
		this.createdDate = createdDate;
		this.imageName = imageName;
		this.user = user;
		this.comments = comments;
	}

	public Blog() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	
}
