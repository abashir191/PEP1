package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.util.Optional;

import com.cognixia.jump.connection.ConnectionManager;

public class UserDaoImpl implements UserDao {
	
	private Connection connection = null;

	@Override
	public void establishConnection() throws ClassNotFoundException, SQLException {
		
		if(connection == null) {
			connection = ConnectionManager.getConnection();
		}
	}
	
	@Override
	public void closeConnection() throws SQLException {
		connection.close();
	}

	@Override
	public boolean logIn(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TVShow getTVShowById(int showId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TVShow> getAllTVShows() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TVShow addTVShow(TVShow tvShow) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TVShow updateTVShow(TVShow tvShow) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteTVShow(int showId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<UserShow> getAllUserShows(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserShow updateUserShow(UserShow userShow) {
		// TODO Auto-generated method stub
		return null;
	}
	
}