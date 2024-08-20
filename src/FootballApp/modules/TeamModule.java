package FootballApp.modules;

import FootballApp.databases.TeamDB;
import FootballApp.entities.Fixture;
import FootballApp.entities.Manager;
import FootballApp.modules.ManagerModule;
import FootballApp.entities.Player;
import FootballApp.entities.Team;
import FootballApp.utility.DataGenerator;
import FootballApp.utility.DataIO;

import javax.xml.crypto.Data;
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
			System.out.println("\n---------Team Menu--------------");
			System.out.println("1-List of Teams");
			System.out.println("2-Find Team by ID");
			System.out.println("3-Find Team by Name");
			System.out.println("8-fikstur");
			System.out.println("9-My Team");
			System.out.println("0-Main Menu");
			System.out.print("Selection: ");
			try {
				userInput = sc.nextInt();
				sc.nextLine();
				if(userInput >= 0 && userInput <= 3 || userInput==9 || userInput==8) {
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
	
	private static void teamMenuSelection(int userInput) {
		switch (userInput) {
			case 1 -> displayTeams(LogInModule.loggedManager);
			case 2 -> displayTeamByID();
			case 3 -> displayTeamByName();
			case 8 -> displayTeamFixtureByName();
			case 9 -> displayManagersTeam(LogInModule.loggedManager);
			case 0 -> System.out.println("\nReturning to Main Menu...\n");
			default-> System.out.println("Please enter a valid value!");
		}
	}

	private static void displayTeamFixtureByName() {
		System.out.println("Enter a team name:");
		String name=sc.nextLine();
		Optional<Fixture> fixtureByID = DataIO.fixtureDB.findByID(DataIO.teamDB.findByName(name).get().getId());
		Fixture fixture=null;
		if(fixtureByID.isPresent()){
			fixture=fixtureByID.get();
			Fixture.printFixtureDetailsOfATeam(fixture,name);
		}

	}

	private static Team displayManagersTeam(Manager manager) {
		Optional<Team> byID = DataIO.teamDB.findByID(manager.getCurrentTeamID());
		Team team=null;
		if(byID.isPresent()){
			team = byID.get();
			System.out.println(team);
			List<Player> byTeamName = DataIO.playerDB.findByTeamName(team.getTeamName());
			for (Player player:byTeamName){
				System.out.println(player);
			}
		}
		return team;
	}

	private static void displayTeamByName() {
		System.out.print("\nEnter the Team Name (0=Back to Team Menu): ");
		String teamName = sc.nextLine();
		
		List<Team> byTeamName = DataIO.teamDB.findByTeamName(teamName);
		if (byTeamName.isEmpty()) {
			System.out.println("Team not found!");
			return;
		}
		if (teamName.equalsIgnoreCase("0")) {
			return;
		}
		System.out.println();
		if(teamName.equalsIgnoreCase("BYE")){
			System.out.println("Team not found!");
			return;
		}
		else{
			byTeamName.forEach(team -> System.out.println("TeamID: " + team.getId() + " TeamName: " + team.getTeamName()));
		}
		displayTeamDetails();
	}
	
	private static void displayTeamByID() {
		try {
			System.out.print("\nEnter Team ID (0=Back to Team Menu): ");
			Integer teamID = sc.nextInt();
			sc.nextLine();
			Optional<Team> teamByID = DataIO.teamDB.findByID(teamID);

			if (teamByID.isPresent()) {
				if (teamByID.get().getTeamName().equals("BYE")){
					System.out.println("Team not found!");
				}
				else {
					System.out.println("\n" + teamByID.get());
				}
			}
			else {
				if (teamID == 0) {
					return;
				}
				System.out.println("\nTeam not found!");
			}
		}catch (InputMismatchException e){
			System.out.println("\nPlease enter a numeric value!");
            sc.next();
		}

	}
	
	public static void displayTeams(Manager manager) {
		System.out.println("\n-------------List of Teams-----------------------------------");
		List<Team> teams = DataIO.teamDB.listAll();
		Optional<Team> byID = DataIO.teamDB.findByID(manager.getCurrentTeamID());
		if(byID.isPresent()) {
			System.out.println("Manager's Current Team:  ");
			System.out.println(byID.get());
		}
		System.out.println("\nOther Teams:  ");
		teams.stream().filter(team -> team.getId()!= manager.getCurrentTeamID() && !(team.getTeamName().equals("BYE"))).forEach(team -> System.out.println("TeamID: " + team.getId() + " Team Name: " + team.getTeamName()));
		displayTeamDetails();
	}
	
	public static void displayTeamDetails() {
		try {
			System.out.print("\nWhich team do you want to select? Please enter the Team ID (0=Back to Team Menu): ");
			Integer teamID = sc.nextInt();
			if(teamID==0){
				return;
			}
			Optional<Team> teamByID = DataIO.teamDB.findByID(teamID);
			if (teamByID.isPresent()) {
				System.out.println("\nTeam Details:  ");
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
				System.out.println("\nTeam Players:  ");
				players.forEach(System.out::println);
			}
		}catch (InputMismatchException e){
			System.out.println("\nPlease enter a numeric value!");
			sc.next();
		}

	}
}