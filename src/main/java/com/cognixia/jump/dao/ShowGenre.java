package com.cognixia.jump.dao;

public class ShowGenre {
	
	private int showGenre_id;
	private int show_id;
	private int genre_id;
	
	public ShowGenre(int showGenre_id, int show_id, int genre_id) {
		
		super();
		this.showGenre_id = showGenre_id;
		this.show_id = show_id;
		this.genre_id = genre_id;
		
	}

	public int getShowGenre_id() {
		return showGenre_id;
	}

	public void setShowGenre_id(int showGenre_id) {
		this.showGenre_id = showGenre_id;
	}

	public int getShow_id() {
		return show_id;
	}

	public void setShow_id(int show_id) {
		this.show_id = show_id;
	}

	public int getGenre_id() {
		return genre_id;
	}

	public void setGenre_id(int genre_id) {
		this.genre_id = genre_id;
	}
	
	@Override
	public String toString() {
		return "ShowGenre [id=" + showGenre_id + ", show id=" + show_id + ", genre_id=" + genre_id + "]";
	}
	
}