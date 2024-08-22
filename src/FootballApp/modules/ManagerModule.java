package FootballApp.modules;


import FootballApp.entities.Manager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ManagerModule {
	static Scanner sc = new Scanner(System.in);
	
	public static void startManagerMenu() {
		int userInput;
		do {
			userInput = managerMenuEntry();
			managerMenuSelection(userInput);
		} while (userInput != 0);
	}
	
	private static int managerMenuEntry() {
		int userInput = -1;
		boolean validInput = false;
		
		while (!validInput) {
				System.out.println("1-Team View");
				System.out.println("2-Player View");
				System.out.println("3-Manager Information");
				System.out.println("0-Main Menu");
				System.out.print("Selection: ");
			
			try {
				userInput = sc.nextInt();
				sc.nextLine();
				if (userInput >= 0 && userInput <=3) {
					validInput = true;
				}
				else {
					System.out.println("Please enter a valid option!\n");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nPlease enter a numeric value!");
				sc.next();
			}
		}
		return userInput;
	}
	
	private static void managerMenuSelection(int userInput) {
		switch (userInput) {
			case 1 -> managerTeamDisplay(LogInModule.loggedManager);
	//		case 2 -> managerPlayerDisplay();
	//		case 3 -> managerInfoDisplay();
			case 0 -> System.out.println("Returning to Main Menu...");
			default -> System.out.println("Please enter a valid value!");
		}
	}
	
	private static void managerTeamDisplay(Manager loggedManager) {
		System.out.println("1-");
	
	}
	
	
}