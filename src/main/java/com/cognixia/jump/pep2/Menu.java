package com.cognixia.jump.pep2;

import java.util.Scanner;

public class Menu {
	Scanner sc = new Scanner(System.in);

	public void mainMenu() {
		boolean loggedIn = false;
		//do login menu logic here 
		
		while(loggedIn) {
			System.out.println("What would you like to do? Please select an option below:\n"
					+ "1. Add a new TV show entry\n"
					+ "2. Modify an existing TV show's status\n"
					+ "3. Delete an existing TV show entry\n"
					+ "4. Logout");
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
			//log out
			case 4:
				loggedIn = false;
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
}
