package com.cognixia.jump.dao;

public class UserShow {
	
	private int usershow_id;
	private int user_id;
	private int show_id;
	private String status;
	private double indiv_rating;
	private int ep_watched;
	
	public UserShow (int usershow_id, int user_id, int show_id, String status, double indiv_rating, int ep_watched) {
		
		super();
		this.usershow_id = usershow_id;
		this.user_id = user_id;
		this.show_id = show_id;
		this.status = status;
		this.indiv_rating = indiv_rating;
		this.ep_watched = ep_watched;
		
	}

	public double getIndiv_rating() {
		return indiv_rating;
	}

	public void setIndiv_rating(double indiv_rating) {
		this.indiv_rating = indiv_rating;
	}

	public int getEp_watched() {
		return ep_watched;
	}

	public void setEp_watched(int ep_watched) {
		this.ep_watched = ep_watched;
	}

	public int getUsershow_id() {
		return usershow_id;
	}

	public void setUsershow_id(int usershow_id) {
		this.usershow_id = usershow_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getShow_id() {
		return show_id;
	}

	public void setShow_id(int show_id) {
		this.show_id = show_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "UserShow [id=" + usershow_id + ", user id=" + user_id + ", show id=" + show_id + ", status=" + status + ", your rating=" + indiv_rating + " # of ep watched=" + ep_watched + "]";
	}
	
}