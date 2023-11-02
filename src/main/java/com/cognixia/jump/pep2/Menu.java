package com.cognixia.jump.pep2;

import com.cognixia.jump.dao.TVShow;
import com.cognixia.jump.dao.User;
import com.cognixia.jump.dao.UserDao;
import com.cognixia.jump.dao.UserDaoImpl;
import com.cognixia.jump.dao.UserShow;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class Menu {
	Scanner sc = new Scanner(System.in);
	UserDao udi = new UserDaoImpl();
	boolean quitProgram = false;
	int liUserId = 0;
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_GREEN = "\u001B[32m";
	
	public void mainMenu() {
		
		try {
			udi.establishConnection();
		} catch (ClassNotFoundException | SQLException e1) {
			System.out.println("\nCould not connect to the Database, application cannot run at this time.");
		};
	
		while(!quitProgram) {
			boolean loggedIn = false;
			//do login menu logic here  - query for true or false to log in
			while(!loggedIn && !quitProgram) {
				//returns true or false based on whether operation was successful
				loggedIn = loginMenu();

				//query for user info, do an if statement if user data does not exist to make them repeat
			}

			while(loggedIn && !quitProgram) {
				System.out.println(ANSI_CYAN + "What would you like to do? Please select an option below:\n"
						+ "1. Add a new TV show entry (admin only)\n"
						+ "2. Modify an existing TV show's rating (admin only)\n"
						+ "3. Delete an existing TV show entry (admin only)\n"
						+ "4. View your shows\n"
						+ "5. Add to your shows\n"
						+ "6. Delete from your shows\n"
						+ "7. Update from your shows\n"
						+ "8. Get average rating for a TV show\n"
						+ "9. View TV catalog\n"
						+ "10. Log out" + ANSI_RESET);
				int input = sc.nextInt();
				sc.nextLine();

				switch(input) {

				//add a new TV show entry
				case 1:
					createTVShow();
					continue;
				//modify an existing TV show entry
				case 2:
					modifyTVShow();
					continue;
				//delete an existing TV show entry
				case 3:
					deleteTVShow();
					continue;
				//get your shows
				case 4:
					viewYourShows();
					continue;
				case 5:
					addYourShow();
					continue;
				case 6:
					deleteYourShow();
					continue;
				case 7:
					updateYourShow();
					continue;
				//Avg rating from a TV show
				case 8:
					rateTVShow();
					continue;
				//View TV catalog
				case 9:
					viewTVCatalog();
					continue;
				//log out
				case 10:
					System.out.println(ANSI_GREEN + "--------------------" + ANSI_RESET);
					System.out.println("Log out successful.");
					loggedIn = false;
					liUserId = 0;
					continue;
				default:
					System.out.println("Invalid input - please select a valid option.");
				}
			}
		}
	}

	public void createTVShow() {
		if (liUserId == 1) {
			System.out.println("Please enter the name of the TV show you would like to add: ");
			String showInput = sc.nextLine();
			//logic for finding if the show exists here, exit if it does not exist
	
	
			// System.out.println("What is your current watch status of this show?\n"
			//		+ "1. Not watched\n"
			//		+ "2. Currently watching\n"
			//		+ "3. Finished");
			// int input = sc.nextInt();
			// sc.nextLine();
	
			System.out.println("What do you rate the show? (1-5)\n");
			double ratinginput = sc.nextDouble();
			
			System.out.println("How many episodes are there? \n");
			int maxep = sc.nextInt();
			
			TVShow newShow = new TVShow(0, showInput, ratinginput, maxep);
			System.out.println(ANSI_GREEN + "--------------------" + ANSI_RESET);
			if (udi.addTVShow(newShow) != null) {
				System.out.println("Show successfully created.");
			} else {
				System.out.println("Show NOT successfully created.");
			}
			System.out.println(ANSI_GREEN + "--------------------" + ANSI_RESET);
		} else {
			System.out.println(ANSI_GREEN + "--------------------" + ANSI_RESET);
			System.out.println("You do not have admin privilege to create a TV show.");
			System.out.println(ANSI_GREEN + "--------------------" + ANSI_RESET);
		}
	}

	public void modifyTVShow() {
		if (liUserId == 1) {
			System.out.println("Please enter the ID of the TV show whose rating you would like to modify: ");
			int showInput = sc.nextInt();
			//logic for finding if the show exists here, exit if it does not exist
			System.out.println("What is the updated rating for the show? (1-5)");
			int input = sc.nextInt();
			sc.nextLine();
			TVShow oldShow = udi.getTVShowById(showInput);
			TVShow updatedShow = new TVShow(showInput, oldShow.getShow_name(), input, oldShow.getMax_episode());
			System.out.println(ANSI_GREEN + "--------------------" + ANSI_RESET);
			if (udi.updateTVShow(updatedShow)!= null) {
				System.out.println("Show successfully updated.");
			} else {
				System.out.println("Show NOT successfully updated. Please make sure show with the given ID exists.");
			}
			System.out.println(ANSI_GREEN + "--------------------" + ANSI_RESET);
		} else {
			System.out.println(ANSI_GREEN + "--------------------" + ANSI_RESET);
			System.out.println("You do not have admin privilege to modify a TV show.");
			System.out.println(ANSI_GREEN + "--------------------" + ANSI_RESET);
		}
	}

	public void deleteTVShow() {
		if (liUserId == 1) {
			System.out.println("Please enter the ID of the TV show you wish to delete: ");
			int input = sc.nextInt();
			System.out.println(ANSI_GREEN + "--------------------" + ANSI_RESET);
			if (udi.deleteTVShow(input)) {
				System.out.println("Show successfully deleted.");
			} else {
				System.out.println("Show NOT successfully deleted.");
			}
			System.out.println(ANSI_GREEN + "--------------------" + ANSI_RESET);
		} else {
			System.out.println(ANSI_GREEN + "--------------------" + ANSI_RESET);
			System.out.println("You do not have admin privilege to delete a TV show.");
			System.out.println(ANSI_GREEN + "--------------------" + ANSI_RESET);
		}
	}

	public void rateTVShow() {
		
		System.out.println("Please enter the name of the TV show you wish to see the average rating for: ");
		String show = sc.nextLine();
		List<TVShow> showByName = udi.getShowByName(show);
		
		int counter = 0;
		double total = 0;
		
		for (TVShow eachShow: showByName) {
			
			counter++;
			total += eachShow.getRating();
			
		}
		
		double d = total / counter;
		System.out.println(ANSI_GREEN + "--------------------" + ANSI_RESET);
		System.out.println("The average rating for the show " + show + " is " + d + " .");
		
//		System.out.println("Please enter the name of the TV show you wish to rate: ");
//		String show = sc.nextLine();
//		//if statement for if show exists, then we can rate it
//		if(show == "") {
//			System.out.println("Please enter your rating for the show (1-5): ");
//			int input = sc.nextInt();
//			sc.nextLine();
//			while(input > 5 || input < 1) {
//				System.out.println("Please enter a valid rating (1-5): ");
//				input = sc.nextInt();
//				sc.nextLine();
//			}
//			//do logic for adding rating to show here
//
//		} else {
//			System.out.println("This TV show is not in the catalog. Sending you back to the menu...");
//		}
		
	}

	public void viewTVCatalog() {
		List<TVShow> allShows = udi.getAllTVShows();
		System.out.println(ANSI_GREEN + "--------------------" + ANSI_RESET);
		System.out.println("The entire catalog: \n");
		for (TVShow show: allShows) {
			System.out.println(show);
		}
		System.out.println(ANSI_GREEN + "--------------------" + ANSI_RESET);
	}

	public boolean loginMenu() {
		System.out.println(ANSI_YELLOW + "Welcome! Please select an option from below: \n"
				+ "1. Existing user log in\n"
				+ "2. Create new account\n"
				+ "3. Quit" + ANSI_RESET);
		int input = sc.nextInt();
		sc.nextLine();

		switch(input) {

		//existing user log in
		case 1:
			System.out.println("Please enter your username: ");
			String username = sc.nextLine();
			System.out.println("Please enter your password: ");
			String password = sc.nextLine();

			//check to see if username and password exist, return true if so
			User usr = new User(0, username, password, "normal");
			//System.out.println(usr);

			System.out.println(ANSI_GREEN + "--------------------" + ANSI_RESET);
			
			int logInChk = udi.logIn(usr);
			
			if (logInChk > 0) {
				liUserId = logInChk;
				System.out.println("Log in successful, welcome " + username + ".");
				System.out.println(ANSI_GREEN + "--------------------" + ANSI_RESET);
				return true;
			}
			else {
				System.out.println("Log in unsuccessful, please check your credentials.");
				System.out.println(ANSI_GREEN + "--------------------" + ANSI_RESET);
				return false;
			}


		//create new account
		case 2:
			System.out.println("Please enter your username: ");
			username = sc.nextLine();
			System.out.println("Please enter your password: ");
			password = sc.nextLine();
			User createdusr = new User(0, username, password, "normal");
			//query to add username and password to table here
			if (udi.addUser(createdusr)) {
				System.out.println(ANSI_GREEN + "--------------------" + ANSI_RESET);
				System.out.println("Account successfully created, username= " + username + ".");
			}
			System.out.println(ANSI_GREEN + "--------------------" + ANSI_RESET);
			return false;

		case 3:
			quitProgram = true;
		default:
			System.out.println("Invalid input, please try again.");
		}
		return false;
	}
	
	public void viewYourShows() {
		Optional<List<UserShow>> yourShows = udi.getAllUserShows(liUserId);
		List<UserShow> showList = yourShows.get();
		System.out.println(ANSI_GREEN + "--------------------" + ANSI_RESET);
		System.out.println("In your list:\n");
		for(UserShow us: showList) {
			TVShow thisShow = udi.getTVShowById(us.getShow_id());
			System.out.println("ListID is " + us.getUsershow_id() + ", name is " + thisShow.getShow_name() + ", show status is: " + us.getStatus() + 
					", you rated it: " + us.getIndiv_rating() + ", progress: " + us.getEp_watched() + "/" + thisShow.getMax_episode());
		}
		System.out.println("\nPlease update as you watch more episodes, or decide on their ratings! (Not watched is default 0 rating).");
		System.out.println(ANSI_GREEN + "--------------------" + ANSI_RESET);
	}
	
	public void addYourShow() {
		System.out.println("Please enter the ID of the TV show you want to add to your list: ");
		int show = sc.nextInt();
		
		UserShow newShow = new UserShow(0, liUserId, show, "Not Started", 0.0, 0);
		System.out.println(ANSI_GREEN + "--------------------" + ANSI_RESET);
		if (udi.addUserShow(newShow)) {
			System.out.println("Show successfully added to your list.");
		} else {
			System.out.println("Show NOT successfully added to your list. Please check the show with the provided ID exists or it's not on your list already.");
		}
		System.out.println(ANSI_GREEN + "--------------------" + ANSI_RESET);
	}
	
	public void deleteYourShow() {
		System.out.println("Please enter the ID of the list entry you want to add to your list: ");
		int show = sc.nextInt();
		System.out.println(ANSI_GREEN + "--------------------" + ANSI_RESET);
		if (udi.deleteUserShow(show, liUserId)) {
			System.out.println("Show successfully deleted from your list.");
		} else {
			System.out.println("Show NOT successfully deleted from your list. Please check the list entry with the provided ID exists or it's on your watch list.");
		}
		System.out.println(ANSI_GREEN + "--------------------" + ANSI_RESET);
	}
	
	public void updateYourShow() {
		System.out.println("Please enter the ID of the list entry you want to modify: ");
		int showid = sc.nextInt();
		sc.nextLine();
		System.out.println("What is your updated status? (Not Started / Watching / Finished)");
		String new_status = sc.nextLine();
		System.out.println("What is your updated rating? (1-5)");
		double new_rating = sc.nextDouble();
		System.out.println("How many episodes did you watch?");
		int ep_watched = sc.nextInt();
		
		Optional<UserShow> foundUSOpt = udi.getUserShowByID(showid, liUserId);
		UserShow foundUS = foundUSOpt.get();
		System.out.println(ANSI_GREEN + "--------------------" + ANSI_RESET);
		if (foundUS == null) {
			System.out.println("The ID either does not exist or is not in your watch list.");
		} else {
			
			int usid = foundUS.getUsershow_id();
			int uid = foundUS.getUser_id();
			int sid = foundUS.getShow_id();
			
			UserShow updatedShow = new UserShow(usid, uid, sid, new_status, new_rating, ep_watched);
			
			if (udi.updateUserShow(updatedShow)) {
				System.out.println("Show in the watch list successfully updated.");
			} else {
				System.out.println("Show in the watch list NOT successfully updated.");
			}
		}
		System.out.println(ANSI_GREEN + "--------------------" + ANSI_RESET);
	}
}

