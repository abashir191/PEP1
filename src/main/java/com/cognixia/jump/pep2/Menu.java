package com.cognixia.jump.pep2;

import java.util.Scanner;
import com.cognixia.jump.connection.*;
import com.cognixia.jump.dao.*;

public class Menu {
	Scanner sc = new Scanner(System.in);
	boolean quitProgram = false;
	public void mainMenu() {
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
						+ "4. Rate a TV show\n"
						+ "5. View TV catalog\n"
						+ "6. Log out");
				int input = sc.nextInt();
				sc.nextLine();
				
				switch(input) {
				
				//add a new TV show entry
				case 1:
					createTVShow();
				//modify an existing TV show entry
				case 2:
					modifyTVShow();
				//delete an existing TV show entry
				case 3:
					deleteTVShow();
					
				//Rate a TV show
				case 4:
					rateTVShow();
				//View TV catalog
				case 5:
					viewTVCatalog();
					
				//log out
				case 6:
					loggedIn = false;
				
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
		
		
		System.out.println("What is your current watch status of this show?\n"
				+ "1. Not watched\n"
				+ "2. Currently watching\n"
				+ "3. Finished");
		int input = sc.nextInt();
		sc.nextLine();
		
		switch(input) {
		
		// status = 'Not Started'
		case 1: 
		
		// status = 'Watching'
		case 2:
		
		// status = 'Finished'	
		case 3:
			
		default:
			
		}
		
	}
	
	public void modifyTVShow() {
		System.out.println("Please enter the name of the TV show whose status you would like to modify: ");
		String showInput = sc.nextLine();
		//logic for finding if the show exists here, exit if it does not exist
		
		
		System.out.println("What is your current watch status of this show?\n"
				+ "1. Not watched\n"
				+ "2. Currently watching\n"
				+ "3. Finished");
		int input = sc.nextInt();
		sc.nextLine();
		
		switch(input) {
		
		// status = 'Not Started'
		case 1: 
		
		// status = 'Watching'
		case 2:
		
		// status = 'Finished'	
		case 3:
			
		}
	}
	
	public void deleteTVShow() {
		System.out.println("Please enter the name of the TV show you wish to delete: ");
		String input = sc.nextLine();
		
		//delete the entry for input if it exists
	}
	
	public void rateTVShow() {
		System.out.println("Please enter the name of the TV show you wish to rate: ");
		String show = sc.nextLine();
		//if statement for if show exists, then we can rate it
		if(show == "") {
			System.out.println("Please enter your rating for the show (1-5): ");
			int input = sc.nextInt();
			sc.nextLine();
			while(input > 5 || input < 1) {
				System.out.println("Please enter a valid rating (1-5): ");
				input = sc.nextInt();
				sc.nextLine();
			}
			//do logic for adding rating to show here
			
		} else {
			System.out.println("This TV show is not in the catalog. Sending you back to the menu...");
		}
	}
	
	public void viewTVCatalog() {
		
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
			logIn(usr);
		//create new account	
		case 2:
			System.out.println("Please enter your username: ");
			username = sc.nextLine();
			System.out.println("Please enter your password: ");
			password = sc.nextLine();
			
			//query to add username and password to table here
			return true;
		
		case 3:
			quitProgram = true;
		default:
			System.out.println("Invalid input, please try again.");
		}
		return false;
	}
}
