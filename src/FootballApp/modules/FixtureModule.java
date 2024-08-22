package FootballApp.modules;

import FootballApp.entities.Fixture;
import FootballApp.entities.League;
import FootballApp.entities.Match;
import FootballApp.entities.Team;
import FootballApp.models.DatabaseModels;
import FootballApp.models.TeamModel;
import FootballApp.utility.DataIO;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class FixtureModule {
	static Scanner sc = new Scanner(System.in);
	public static void startFixtureMenu() {
		int userInput;
		do {
			userInput = fixtureMenuEntry();
			fixtureMenuSelection(userInput);
		} while (userInput != 0);
	}
	
	private static int fixtureMenuEntry() {
		int userInput=-1;
		boolean validInput = false;
		
		while(!validInput) {
			System.out.println("\n---------Fixture Menu--------------");
			System.out.println("1-Fixture By League ID");
			System.out.println("2-Fixture By Team Name");
			System.out.println("0-Main Menu");
			System.out.print("Selection: ");
			try {
				userInput = sc.nextInt();
				sc.nextLine();
				if(userInput >= 0 && userInput <= 2) {
					validInput = true;
				}
				else {
					System.out.println("\nPlease enter a valid option!");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nPlease enter a numeric value!");
				sc.next();
			}
		}
		return userInput;
	}
	private static void fixtureMenuSelection(int userInput) {
		switch (userInput) {
			case 1 -> displayFixtureByLeague();
			case 2 -> displayTeamFixtureByName();
			case 0 -> System.out.println("\nReturning to Main Menu...\n");
			default-> System.out.println("Please enter a valid value!");
		}
	}
	
	private static void displayTeamFixtureByName() {
		System.out.println("Enter a team name:");
		String name=sc.nextLine();
		List<Match> byTeamName = DatabaseModels.matchDB.findByTeamName(name);
		System.out.println(byTeamName);
		
	}
	
	private static void displayFixtureByLeague() {
		System.out.println("Enter a League ID:");
		Integer id=sc.nextInt();
		Optional<Fixture> byID = DatabaseModels.fixtureDB.findByID(DatabaseModels.leagueDB.findByID(id).get().getLeagueFixtureID());
		Fixture fixture=null;
		if(byID.isPresent()){
			fixture=byID.get();
			Fixture.printFixtureDetails(fixture);
		}
	}
}