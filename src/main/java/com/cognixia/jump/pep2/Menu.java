package com.cognixia.jump.pep2;

import com.cognixia.jump.dao.TVShow;
import com.cognixia.jump.dao.User;
import com.cognixia.jump.dao.UserDao;
import com.cognixia.jump.dao.UserDaoImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class Menu {
	Scanner sc = new Scanner(System.in);
	UserDao udi = new UserDaoImpl();
	boolean quitProgram = false;
	
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
				System.out.println("What would you like to do? Please select an option below:\n"
						+ "1. Add a new TV show entry\n"
						+ "2. Modify an existing TV show's status\n"
						+ "3. Delete an existing TV show entry\n"
						+ "4. Get average rating for a TV show\n"
						+ "5. View TV catalog\n"
						+ "6. Log out");
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
				//Rate a TV show
				case 4:
					rateTVShow();
					continue;
				//View TV catalog
				case 5:
					viewTVCatalog();
					continue;
				//log out
				case 6:
					loggedIn = false;
					continue;
				default:
					System.out.println("Invalid input - please select a valid option.");
				}
			}
		}
	}

	public void createTVShow() {
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
		
		TVShow newShow = new TVShow(0, showInput, ratinginput);

		if (udi.addTVShow(newShow) != null) {
			System.out.println("Show successfully created");
		} else {
			System.out.println("Show NOT successfully created");
		}
	}

	public void modifyTVShow() {
		System.out.println("Please enter the ID of the TV show whose status you would like to modify: ");
		int showInput = sc.nextInt();
		//logic for finding if the show exists here, exit if it does not exist
		System.out.println("What is the updated rating for the show? (1-5)");
		int input = sc.nextInt();
		sc.nextLine();
		TVShow oldShow = udi.getTVShowById(showInput);
		TVShow updatedShow = new TVShow(showInput, oldShow.getShow_name(), input);
		if (udi.updateTVShow(updatedShow)!= null) {
			System.out.println("Show successfully updated");
		} else {
			System.out.println("Show NOT successfully updated");
		}
	}

	public void deleteTVShow() {
		System.out.println("Please enter the ID of the TV show you wish to delete: ");
		int input = sc.nextInt();
		if (udi.deleteTVShow(input)) {
			System.out.println("Show successfully deleted");
		} else {
			System.out.println("Show NOT successfully deleted");
		}
	}

	public void rateTVShow() {
		
		System.out.println("Please enter the name of the TV show you wish to rate: ");
		String show = sc.nextLine();
		List<TVShow> showByName = udi.getShowByName(show);
		
		int counter = 0;
		double total = 0;
		
		for (TVShow eachShow: showByName) {
			
			counter++;
			total += eachShow.getRating();
			
		}
		
		double d = total / counter;
		System.out.println("The average rating for the show " + show + " is " + d);
		
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
		for (TVShow show: allShows) {
			System.out.println(show);
		}
	}

	public boolean loginMenu() {
		System.out.println("Welcome! Please select an option from below: \n"
				+ "1. Existing user log in\n"
				+ "2. Create new account\n"
				+ "3. Quit");
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
			User usr = new User(username, password, "normal");
			//System.out.println(usr);

			if (udi.logIn(usr)) {
				System.out.println("YAY ITS TRUE");
				return true;
			}
			else {
				System.out.println("NOOOOO. BAD");
				return false;
			}


		//create new account
		case 2:
			System.out.println("Please enter your username: ");
			username = sc.nextLine();
			System.out.println("Please enter your password: ");
			password = sc.nextLine();
			User createdusr = new User(username, password, "normal");
			//query to add username and password to table here
			if (udi.addUser(createdusr)) {
				System.out.println("Account successfully created, id=" + username);
			}
			return false;

		case 3:
			quitProgram = true;
		default:
			System.out.println("Invalid input, please try again.");
		}
		return false;
	}
}

