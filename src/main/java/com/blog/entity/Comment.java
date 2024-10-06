package com.blog.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String commentDescription;
	private String commentedBy;
	private Date commentedOn=new Date();
	
	@ManyToOne
    @JoinColumn(name = "blog_id")
	@JsonBackReference
    private Blog blog;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCommentDescription() {
		return commentDescription;
	}

	public void setCommentDescription(String commentDescription) {
		this.commentDescription = commentDescription;
	}

	public String getCommentedBy() {
		return commentedBy;
	}

	public void setCommentedBy(String commentedBy) {
		this.commentedBy = commentedBy;
	}

	public Date getCommentedOn() {
		return commentedOn;
	}

	public void setCommentedOn(Date commentedOn) {
		this.commentedOn = commentedOn;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", commentDescription=" + commentDescription + ", commentedBy=" + commentedBy
				+ ", commentedOn=" + commentedOn + ", blog=" + blog + "]";
	}

	public Comment(int id, String commentDescription, String commentedBy, Date commentedOn, Blog blog) {
		super();
		this.id = id;
		this.commentDescription = commentDescription;
		this.commentedBy = commentedBy;
		this.commentedOn = commentedOn;
		this.blog = blog;
	}

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
