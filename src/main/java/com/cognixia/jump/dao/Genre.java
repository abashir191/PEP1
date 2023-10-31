package com.cognixia.jump.dao;

public class Genre {
	
	private int genre_id;
	private String genre_name;
	
	public Genre(int genre_id, String genre_name) {
		
		super();
		this.genre_id = genre_id;
		this.genre_name = genre_name;
		
	}

	public int getGenre_id() {
		return genre_id;
	}

	public void setGenre_id(int genre_id) {
		this.genre_id = genre_id;
	}

	public String getGenre_name() {
		return genre_name;
	}

	public void setGenre_name(String genre_name) {
		this.genre_name = genre_name;
	}
	
	@Override
	public String toString() {
		return "Genre [id=" + genre_id + ", name=" + genre_name + "]";
	}
	
}