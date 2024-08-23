package FootballApp.modules;

import FootballApp.entities.League;
import FootballApp.entities.Manager;
import FootballApp.entities.Team;
import FootballApp.models.DatabaseModels;
import FootballApp.models.FixtureModel;
import FootballApp.models.TeamModel;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class MyTeamModule {
	static Scanner sc = new Scanner(System.in);
	
	public static void startMyTeamMenu() {
		int userInput;
		do {
			userInput = myTeamMenuEntry();
			myTeamMenuSelection(userInput);
		} while (userInput != 0);
	}
	
	private static int myTeamMenuEntry() {
		int userInput=-1;
		boolean validInput = false;
		
		while(!validInput) {
			System.out.println("\n---------MyTeam--------------");
			System.out.println("1-Details of My Team");
			System.out.println("2-Players of My Team");
			System.out.println("3-Fixture of My Team");
			System.out.println("4-Match Results of My Team");
			System.out.println("9-Manager Information");
			System.out.println("0-Main Menu");
			System.out.print("Selection: ");
			try {
				userInput = sc.nextInt();
				sc.nextLine();
				if(userInput >= 0 && userInput <= 4 || userInput == 9) {
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
	
	private static void myTeamMenuSelection(int userInput) {
		switch (userInput) {
			case 1 -> displayMyTeam(LogInModule.loggedManager);
			case 2 -> displayMyPlayer(LogInModule.loggedManager);
			case 3 -> displayMyFixture(LogInModule.loggedManager);
			case 4 -> displayMyResults(LogInModule.loggedManager);
			case 9 -> displayMyInfo(LogInModule.loggedManager);
			case 0 -> System.out.println("\nReturning to Main Menu...\n");
			default-> System.out.println("Please enter a valid value!");
		}
	}
	
	private static void displayMyInfo(Manager loggedManager) {
		System.out.println("\nManager Information:");
        System.out.println("Name              : " + loggedManager.getName());
        System.out.println("Surname           : " + loggedManager.getSurName());
        System.out.println("Age               : " + loggedManager.getAge());
        System.out.println("Nationality       : " + loggedManager.getNationality());
        System.out.println("Current Team Name : " + DatabaseModels.teamDB.findByID(loggedManager.getCurrentTeamID()).get().getTeamName());
		System.out.println("---------------------------------------------------------");
  
	}
	
	private static void displayMyResults(Manager loggedManager) {
		System.out.println("\nMatch Results of My Team:");
		Team team = DatabaseModels.teamDB.findByID(loggedManager.getCurrentTeamID()).get();
        TeamModel teamModel=new TeamModel(DatabaseModels.getInstance(), team);
		teamModel.displayConcludedMatchesofaTeam();
	}
	
	private static void displayMyFixture(Manager loggedManager) {
		System.out.println("\nFixture of My Team:");
		Team team = DatabaseModels.teamDB.findByID(loggedManager.getCurrentTeamID()).get();
		Optional<League> byID = DatabaseModels.leagueDB.findByID(team.getLeagueID());
		if(byID.isPresent()){
			FixtureModel fixtureModel=new FixtureModel(DatabaseModels.getInstance(), byID.get());
			fixtureModel.displayTeamFixture(team.getTeamName());
		}
		else{
            System.out.println("Manager is not assigned to any league. Please assign a league to the team first.");
        }
		
	}
	
	private static void displayMyPlayer(Manager loggedManager) {
		System.out.println("\nPlayers of My Team:");
		TeamModel teamModel=new TeamModel(DatabaseModels.getInstance(), DatabaseModels.teamDB.findByID(loggedManager.getCurrentTeamID()).get());
		teamModel.displayTeamPlayers();
	}
	
	private static void displayMyTeam(Manager loggedManager) {
		System.out.println("\nDetails of My Team:");
		TeamModel teamModel=new TeamModel(DatabaseModels.getInstance(), DatabaseModels.teamDB.findByID(loggedManager.getCurrentTeamID()).get());
		teamModel.displayClubInfo();
	}
}