package com.cognixia.jump.dao;

import com.cognixia.jump.connection.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM User WHERE username = ? AND password = ?")) {
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());

			ResultSet rs = ps.executeQuery();

			// Check if the user with the provided username and password exists
			if (rs.next()) {
				rs.close();
				return true; // Return true if the user can log in
			} else {
				rs.close();
				return false; // Return false if the user cannot log in
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false; // Return false if an SQL exception occurs during the login attempt
		}
	}

//Methods:
	@Override
	public Optional<User> getUserById(int userId) {
		try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM User WHERE user_id = ?")) {
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();

			// Check if user with the specified ID was found
			if (rs.next()) {
				int user_Id = rs.getInt("user_id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String role = rs.getString("role");

				rs.close();

				// Create a new User object and put it in Optional
				User user = new User(userId, username, password, role);
				Optional<User> userFound = Optional.of(user);
				return userFound;

			} else {
				rs.close();
				// Return an empty Optional if the user isn't found with the given ID
				return Optional.empty();
			}

		} catch (SQLException e) {
			// Handle any SQL exception and return an empty Optional
			return Optional.empty();
		}
	}


	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();

		try {
			// Create a PreparedStatement
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM User");

			// Execute the query to get the set of results
			ResultSet rs = ps.executeQuery();

			// Iterate through the result set and input users into list
			while (rs.next()) {
				User user = new User(
						rs.getInt("user_id"), // update columns in table
						rs.getString("username"),
						rs.getString("password"),
						rs.getString("role")
				);
				users.add(user);
			}

			// Close the statement and set
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;

	}

	@Override
	public User addUser(User user) {
		try {

			// Create a PreparedStatement
			PreparedStatement ps = connection.prepareStatement("INSERT INTO User (username, password, role) VALUES (?, ?, ?)");
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, "normal");

			int count = ps.executeUpdate();

			if (count > 0) {
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public TVShow getTVShowById(int showId) {
		String query = "SELECT * FROM TVShow WHERE show_id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, showId);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					int show_id = resultSet.getInt("show_id");
					String show_name = resultSet.getString("show_name");
					double rating = resultSet.getDouble("rating");
					return new TVShow(show_id, show_name, rating);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<TVShow> getAllTVShows() {
		List<TVShow> tvShows = new ArrayList<>();
		String query = "SELECT * FROM TVShow";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query);
			 ResultSet resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				int show_id = resultSet.getInt("show_id");
				String show_name = resultSet.getString("show_name");
				double rating = resultSet.getDouble("rating");
				TVShow tvShow = new TVShow(show_id, show_name, rating);
				tvShows.add(tvShow);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tvShows;
	}
	@Override
	public TVShow addTVShow(TVShow tvShow) {
		String query = "INSERT INTO TVShow (show_name, rating) VALUES (?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setString(1, tvShow.getShow_name());
			preparedStatement.setDouble(2, tvShow.getRating());
			int affectedRows = preparedStatement.executeUpdate();
			if (affectedRows == 1) {
				try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						int show_id = generatedKeys.getInt(1);
						tvShow.setShow_id(show_id);
						return tvShow;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public TVShow updateTVShow(TVShow tvShow) {
		String query = "UPDATE TVShow SET show_name = ?, rating = ? WHERE show_id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, tvShow.getShow_name());
			preparedStatement.setDouble(2, tvShow.getRating());
			preparedStatement.setInt(3, tvShow.getShow_id());
			int affectedRows = preparedStatement.executeUpdate();
			if (affectedRows == 1) {
				return tvShow;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	//bla bla
	@Override
	public boolean deleteTVShow(int showId) {
		String query = "DELETE FROM TVShow WHERE show_id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, showId);
			int affectedRows = preparedStatement.executeUpdate();
			return affectedRows == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;

		}
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