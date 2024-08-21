package FootballApp.modules;

import FootballApp.entities.Fixture;
import FootballApp.entities.Manager;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
	private static Scanner sc = new Scanner(System.in);
	private static Manager loggedManager = null;
	
	
	public static void startMenu() {
		int userInput;
		do {
			userInput = menuEntry();
			menuSelection(userInput);
		} while (userInput != 0);
	}

	private static int menuEntry() {
		int userInput = -1;
		boolean validInput = false;

		while (!validInput) {
			if (loggedManager == null)
				System.out.println("1-Log In");
			System.out.println("2-Team Menu");
			System.out.println("3-Player Menu");
			System.out.println("4-Fixture Menu");
			if (loggedManager != null)
				System.out.println("9-Log Out");
			System.out.println("0-Exit");
			System.out.print("Selection: ");
			try {
				userInput = sc.nextInt();
				sc.nextLine();

				if (loggedManager == null) {
					if (userInput == 1 || userInput == 0) {
						validInput = true;
					} else if (userInput == 2 || userInput == 3|| userInput == 4) {
						System.out.println("\nYou need to log in first!");
					} else {
						System.out.println("\nPlease enter a valid value!\n");
					}
				} else {  // loggedManager != null
					if (userInput == 2 || userInput == 3 || userInput == 9 || userInput == 0|| userInput == 4) {
						validInput = true;
					} else {
						System.out.println("\nPlease enter a valid value!");
					}
				}
			} catch (InputMismatchException e) {
				System.out.println("\nPlease enter a numeric value!");
				sc.next();
			}
		}
		return userInput;
	}

	private static void menuSelection(int userInput) {
		switch (userInput) {
			case 1 -> loggedManager=LogInModule.managerLogIn();
			case 2 -> TeamModule.startTeamMenu();
			case 3 -> PlayerModule.playerMenu();
			case 4 -> FixtureModule.startFixtureMenu();
			case 9 -> loggedManager=LogInModule.managerLogOut();
			case 0 -> System.out.println("\nHave a nice day!");
			default-> System.out.println("\nPlease enter a valid value!");
		}
	}
	
}