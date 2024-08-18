package FootballApp.modules;

import FootballApp.databases.TeamDB;
import FootballApp.entities.Manager;
import FootballApp.modules.ManagerModule;
import FootballApp.entities.Player;
import FootballApp.entities.Team;
import FootballApp.utility.DataGenerator;
import FootballApp.utility.DataIO;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class TeamModule {
	static TeamDB teamDB = new TeamDB();
	static Scanner sc = new Scanner(System.in);
	
	public static void startTeamMenu() {
		int userInput;
		do {
			userInput = teamMenuEntry();
			teamMenuSelection(userInput);
		} while (userInput != 0);
	}
	
	private static int teamMenuEntry() {
		int userInput=-1;
		boolean validInput = false;
		
		while(!validInput) {
			System.out.println("1-List of Teams");
			System.out.println("2-Find Team by ID");
			System.out.println("3-Find Team by Name");
			System.out.println("0-Main Menu");
			System.out.print("Selection: ");
			try {
				userInput = sc.nextInt();
				sc.nextLine();
				if(userInput >= 0 && userInput <= 3) {
					validInput = true;
				}
				else {
					System.out.println("Please enter a valid option!");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nPlease enter a numeric value!");
				sc.next();
			}
		}
		return userInput;
	}
	
	private static void teamMenuSelection(int userInput) {
		switch (userInput) {
			case 1 -> displayTeams(LogInModule.loggedManager);
			case 2 -> displayTeamByID();
			case 3 -> displayTeamByName();
			case 0 -> System.out.println("Returning to Main Menu...");
			default-> System.out.println("Please enter a valid value!");
		}
	}
	
	private static void displayTeamByName() {
		System.out.println("Enter the Team Name: 0=Back to Team Menu");
		String teamName = sc.nextLine();
		
		List<Team> byTeamName = DataIO.teamDB.findByTeamName(teamName);
		if (byTeamName.isEmpty()) {
			if (teamName.equalsIgnoreCase("0")) {
                return;
            }
			System.out.println("Team not found!");
			return;
		}
		byTeamName.forEach(team -> System.out.println("TeamID: " + team.getId() + " TeamName: " + team.getTeamName()));
		displayTeamDetails();
	}
	
	private static void displayTeamByID() {
		System.out.println("Enter Team ID: 0=Back to Team Menu");
		Integer teamID = sc.nextInt();
		sc.nextLine();
		Optional<Team> teamByID = DataIO.teamDB.findByID(teamID);
		if (teamByID.isPresent()) {
			System.out.println(teamByID.get());
		}
		else {
			if (teamID == 0) {
				return;
			}
			System.out.println("Team not found!");
		}
	}
	
	public static void displayTeams(Manager manager) {
		System.out.println("List of Teams");
		List<Team> teams = DataIO.teamDB.listAll();
		Optional<Team> byID = DataIO.teamDB.findByID(manager.getCurrentTeamID());
		if(byID.isPresent()) {
			System.out.println("\nManager's Current Team:  ");
			System.out.println(byID.get());
		}
		System.out.println("\nOther Teams:  ");
		teams.stream().filter(team -> team.getId()!= manager.getCurrentTeamID()).forEach(team -> System.out.println("TeamID: " + team.getId() + " Team Name: " + team.getTeamName()));
		displayTeamDetails();
	}
	
	public static void displayTeamDetails() {
		System.out.println("Which team do you want to select? Please enter the Team ID: 0=Back to Team Menu");
		Integer teamID = sc.nextInt();
		if(teamID==0){
			return;
		}
		Optional<Team> teamByID = DataIO.teamDB.findByID(teamID);
		if (teamByID.isPresent()) {
			System.out.println("Team Details:  ");
			System.out.println(teamByID.get());
		}
		else {
			System.out.println("There is no team by that ID.");
			return;
		}
		List<Player> players = DataIO.playerDB.findByTeamID(teamID);
		if (players.isEmpty()) {
			System.out.println("No players found for this team.");
		} else {
			System.out.println("Team Players:  ");
			players.forEach(System.out::println);
		}
	}
}