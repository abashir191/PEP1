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
	public Optional<List<UserShow>> getAllUserShows(int id) {
		
		Optional<List<UserShow>> userShowOpt = Optional.empty();
		List<UserShow> userShowList = new ArrayList<>();
		
		try ( PreparedStatement pstmt = connection.prepareStatement("select * from UserShow where user_id = ?") ) {
			
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				int usershow_id = rs.getInt("usershow_id");
				int user_id = rs.getInt("user_id");
				int show_id = rs.getInt("show_id");
				String status = rs.getString("status");
				
				UserShow foundShow = new UserShow(usershow_id, user_id, show_id, status);
				
				userShowList.add(foundShow);
			}
			
		} catch (SQLException e) {

			
		}
		
		userShowOpt = Optional.of(userShowList);
		
		return userShowOpt;
	}

	@Override
	public boolean updateUserShow(UserShow userShow) {
		
		try( PreparedStatement pstmt = connection.prepareStatement("insert into UserShow(user_id, show_id, status) values(?, ?, ?) where user_id = ?") ) {
			
			pstmt.setInt(1, userShow.getUser_id());
			pstmt.setInt(2, userShow.getShow_id());
			pstmt.setString(3, userShow.getStatus());
			pstmt.setInt(4, userShow.getUsershow_id());
			
			int count = pstmt.executeUpdate();
			
			if(count > 0) {
				return true;
			}
			
			
		} catch (SQLException e) {
			
		}
		
		return false;
	}
	
}