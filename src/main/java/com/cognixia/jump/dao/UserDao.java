package com.cognixia.jump.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserDao {
	
	public void establishConnection() throws ClassNotFoundException, SQLException;
	public void closeConnection() throws SQLException ;
	
	// Login
	int logIn(User user);
	
    // User operations
    Optional<User> getUserById(int userId);
    List<User> getAllUsers();
    boolean addUser(User user);

    // TVShow operations
    TVShow getTVShowById(int showId);
    List<TVShow> getAllTVShows();
    TVShow addTVShow(TVShow tvShow);
    TVShow updateTVShow(TVShow tvShow);
    boolean deleteTVShow(int showId);
    List<UserShow> getShowByName(int id);
    
    // TVShow searchTVShow(String string);
    
    // UserShow operations
    Optional<List<UserShow>> getAllUserShows(int id);
    boolean updateUserShow(UserShow userShow);
    boolean addUserShow(UserShow userShow);
    boolean deleteUserShow(int usid, int usrid);
    Optional<UserShow> getUserShowByID(int usid, int usrid);
    Optional<List<UserShow>> getShowStatus(int id);
    
}
