package com.cognixia.jump.dao;

public class TVShow {
	
	private int show_id;
	private String show_name;
	private double rating;
	
	public TVShow(int show_id, String show_name, double rating) {
		
		super();
		this.show_id = show_id;
		this.show_name = show_name;
		this.rating = rating;
		
	}

	public int getShow_id() {
		return show_id;
	}

	public void setShow_id(int show_id) {
		this.show_id = show_id;
	}

	public String getShow_name() {
		return show_name;
	}

	public void setShow_name(String show_name) {
		this.show_name = show_name;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
	
	@Override
	public String toString() {
		return "TVShow [id=" + show_id + ", show name=" + show_name + ", rating=" + rating + "]";
	}
	
}