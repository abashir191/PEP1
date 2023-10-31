package com.cognixia.dao;

import java.util.List;

public interface UserDao {
    // TVShow Operations
    TVShow getTVShowById(int showId);
    String getTVShowNameById(int showId);
    double getTVShowRatingById(int showId);

    // User Operations
    User getUserById(int userId);

    // UserShow Operations
    UserShow getUserShowById(int userShowId);
    List<UserShow> getUserShowsByUserId(int userId);
    boolean updateUserShow(UserShow userShow);
}
