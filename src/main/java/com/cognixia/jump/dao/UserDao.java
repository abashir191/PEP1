package com.cognixia.jump.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserDao {
	
	public void establishConnection() throws ClassNotFoundException, SQLException;
	public void closeConnection() throws SQLException ;
	
	// Login
	boolean logIn(User user);
	
    // User operations
    User getUserById(int userId);
    List<User> getAllUsers();
    User addUser(User user);

    // TVShow operations
    TVShow getTVShowById(int showId);
    List<TVShow> getAllTVShows();
    TVShow addTVShow(TVShow tvShow);
    TVShow updateTVShow(TVShow tvShow);
    boolean deleteTVShow(int showId);
    // TVShow searchTVShow(String string);
    
    // UserShow operations
    Optional<List<UserShow>> getAllUserShows(int id);
    boolean updateUserShow(UserShow userShow);
    
}
