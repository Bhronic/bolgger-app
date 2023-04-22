package com.blogger.model;

public class Blog {
	
	private int id;
	
	private String username;
	
	private String blogTitle;
	
	private String blogContent;

	
	
	public Blog() {
	}

	public Blog(int id, String username, String blogTitle, String blogContent) {
		this.id = id;
		this.username = username;
		this.blogTitle = blogTitle;
		this.blogContent = blogContent;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getBlogContent() {
		return blogContent;
	}

	public void setBlogContent(String blogContent) {
		this.blogContent = blogContent;
	}
	
	

}
