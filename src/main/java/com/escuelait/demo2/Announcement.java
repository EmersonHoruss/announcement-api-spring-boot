package com.escuelait.demo2;

import java.util.ArrayList;
import java.util.List;

public class Announcement {
	private Integer id;
	private String name;
	private String about;
	private String comments;
	//private List<Company> companies;
	
	Announcement(){
		id = -1;
		name = "";
		about = "";
		comments = "";
		//companies = new ArrayList<Company>();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	@Override public String toString() {
		return "ID:" + id + "Name:" + name + "About:" + about + "Comments: " + comments;
	}
}
