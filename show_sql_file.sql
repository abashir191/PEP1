drop database if exists show_db;
create database show_db;
use show_db;

drop table if exists User;
drop table if exists UserShow;
drop table if exists TVShow;
drop table if exists ShowGenre;
drop table if exists Genre;

create table User (
    user_id int primary key auto_increment,
    username varchar(255) unique,
    password varchar(255),
    role varchar(255)
);

create table TVShow (
    show_id int primary key auto_increment,
    show_name varchar(255),
    rating double,
    max_episode int
);

create table Genre (
    genre_id int primary key auto_increment,
    genre_name varchar(255)
);

create table UserShow (
    usershow_id int primary key auto_increment,
    user_id int,
    show_id int,
    status varchar(255),
    indiv_rating double,
    ep_watched int,
    foreign key (user_id) references User(user_id),
    foreign key (show_id) references TVShow(show_id)
);

create table ShowGenre (
    showGenre_id int primary key auto_increment,
    show_id int,
    genre_id int,
    foreign key (show_id) references TVShow(show_id),
    foreign key (genre_id) references Genre(genre_id)
);

-- User accounts
insert into User (username, password, role) values ('admin1', 'password1', 'admin');
insert into User (username, password, role) values ('middleUser', 'password2', 'middle');
insert into User (username, password, role) values ('normyUser', 'password3', 'normy');

-- TV Show
insert into TVShow (show_name, rating, max_episode) values ('House of Dragon S1', 4.1, 10);
insert into TVShow (show_name, rating, max_episode) values ('House of Dragon S2', 4, 10);
insert into TVShow (show_name, rating, max_episode) values ('House of Dragon S3', 4.2, 10);
insert into TVShow (show_name, rating, max_episode) values ('Sword Art Online S1', 5.0, 20);
insert into TVShow (show_name, rating, max_episode) values ('Sword Art Online S2', 4.5, 20);
insert into TVShow (show_name, rating, max_episode) values ('Game of Thrones S1', 4, 8);
insert into TVShow (show_name, rating, max_episode) values ('Game of Thrones S2', 3.2, 9);
insert into TVShow (show_name, rating, max_episode) values ('Game of Thrones S3', 3.7, 11);
insert into TVShow (show_name, rating, max_episode) values ('Game of Thrones S4', 4.9, 15);
insert into TVShow (show_name, rating, max_episode) values ('Breaking Bad S1', 4.9, 15);
insert into TVShow (show_name, rating, max_episode) values ('Breaking Bad S2', 5, 15);


-- Genre
insert into Genre (genre_name) values ('Fantasy');
insert into Genre (genre_name) values ('Adventure');
insert into Genre (genre_name) values ('Romance');
insert into Genre (genre_name) values ('Comedy');
insert into Genre (genre_name) values ('Horror');
insert into Genre (genre_name) values ('Science Fiction');

-- UserShow
insert into UserShow (user_id, show_id, status, indiv_rating, ep_watched) values (1, 1, 'Not Started', 0, 0);
insert into UserShow (user_id, show_id, status, indiv_rating, ep_watched) values (1, 2, 'Watching', 4, 2);
insert into UserShow (user_id, show_id, status, indiv_rating, ep_watched) values (1, 3, 'Not Started', 0, 0);
insert into UserShow (user_id, show_id, status, indiv_rating, ep_watched) values (2, 4, 'Not Started', 0, 0);
insert into UserShow (user_id, show_id, status, indiv_rating, ep_watched) values (2, 5, 'Watching', 5, 3);
insert into UserShow (user_id, show_id, status, indiv_rating, ep_watched) values (2, 6, 'Finished', 5, 20);
insert into UserShow (user_id, show_id, status, indiv_rating, ep_watched) values (3, 8, 'Not Started', 0, 0);
insert into UserShow (user_id, show_id, status, indiv_rating, ep_watched) values (3, 9, 'Watching', 4.5, 10);
insert into UserShow (user_id, show_id, status, indiv_rating, ep_watched) values (3, 10, 'Finished', 4.7, 15);

-- Show Genre
insert into ShowGenre (show_id, genre_id) values (1, 1);
insert into ShowGenre (show_id, genre_id) values (2, 2);
insert into ShowGenre (show_id, genre_id) values (3, 3);
