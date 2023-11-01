package com.cognixia.dao;

import java.util.List;

import com.cognixia.jump.dao.Genre;
import com.cognixia.jump.dao.ShowGenre;
import com.cognixia.jump.dao.TVShow;
import com.cognixia.jump.dao.User;

public interface UserDao {
	
    // User operations
    User getUserById(int userId);
    List<User> getAllUsers();
    User addUser(User user);
    User updateUser(User user);
    boolean deleteUser(int userId);

    // TVShow operations
    TVShow getTVShowById(int showId);
    List<TVShow> getAllTVShows();
    TVShow addTVShow(TVShow tvShow);
    TVShow updateTVShow(TVShow tvShow);
    boolean deleteTVShow(int showId);
    
    
    // ShowGenre operations
    ShowGenre getShowGenreById(int showGenreId);
    List<ShowGenre> getAllShowGenres();
    ShowGenre addShowGenre(ShowGenre showGenre);
    ShowGenre updateShowGenre(ShowGenre showGenre);
    boolean deleteShowGenre(int showGenreId);

    // Genre operations
    Genre getGenreById(int genreId);
    List<Genre> getAllGenres();
    Genre addGenre(Genre genre);
    Genre updateGenre(Genre genre);
    boolean deleteGenre(int genreId);
}
