package com.cognixia.jump.dao;

public class TVShow {
	
	private int show_id;
	private String show_name;
	private double rating;
	private int max_episode;
	
	public TVShow(int show_id, String show_name, double rating, int max_episode) {
		
		super();
		this.show_id = show_id;
		this.show_name = show_name;
		this.rating = rating;
		this.max_episode = max_episode;
		
	}

	public int getMax_episode() {
		return max_episode;
	}

	public void setMax_episode(int max_episode) {
		this.max_episode = max_episode;
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
		return "TVShow [id=" + show_id + ", show name=" + show_name + ", rating=" + rating + ", # of episode=" + max_episode + "]";
	}
	
}