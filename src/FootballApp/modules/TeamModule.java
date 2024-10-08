package FootballApp.modules;

import FootballApp.entities.League;
import FootballApp.entities.Manager;
import FootballApp.entities.Player;
import FootballApp.entities.Team;
import FootballApp.models.DatabaseModels;
import FootballApp.models.PlayerModel;
import FootballApp.models.TeamModel;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class TeamModule {
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
			System.out.println("4-Played Matches of a Team");
			System.out.println("0-Main Menu");
			System.out.print("Selection: ");
			try {
				userInput = sc.nextInt();
				sc.nextLine();
				if(userInput >= 0 && userInput <= 4) {
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
			case 1 -> displayAllTeams(LogInModule.loggedManager);
			case 2 -> displayTeamByID();
			case 3 -> displayTeamByName();
			case 4 -> displayPlayedMatchesTeam();
			case 0 -> System.out.println("\nReturning to Main Menu...\n");
			default-> System.out.println("Please enter a valid value!");
		}
	}
	
	private static void displayPlayedMatchesTeam() {
		System.out.println("Enter a Team ID to view their played matches: ");
		Integer teamID = sc.nextInt();
		Optional<Team> byID = DatabaseModels.teamDB.findByID(teamID);
		if (byID.isPresent()) {
			TeamModel teamModel = new TeamModel(DatabaseModels.getInstance(), byID.get());
			teamModel.displayConcludedMatchesofaTeam();
		}
		
	}
	
	
	private static void displayTeamByName() {
		System.out.print("\nEnter the Team Name (0=Back to Team Menu): ");
		String teamName = sc.nextLine();
		
		List<Team> byTeamName = DatabaseModels.teamDB.findByTeamName(teamName);
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
			Optional<Team> teamByID = DatabaseModels.teamDB.findByID(teamID);

			if (teamByID.isPresent()) {
				if (teamByID.get().getTeamName().equals("BYE")){
					System.out.println("Team not found!");
				}
				else {
					TeamModel teamModel = new TeamModel(DatabaseModels.getInstance(), teamByID.get());
					teamModel.displayClubInfo();
					List<Player> players = DatabaseModels.playerDB.findByTeamID(teamID);
					if (players.isEmpty()) {
						System.out.println("No players found for this team.");
					} else {
						System.out.println("\nTeam Players:  ");
						for (Player player : players) {
							System.out.println(player);
						}
					}
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
	
	public static void displayAllTeams(Manager manager) {
		Integer leagueID = LeagueModule.validLeagueIDControl();
		System.out.println("\n-------------List of Teams-----------------------------------");
		List<Team> teams = DatabaseModels.teamDB.findAllByLeagueID(leagueID);
		Optional<Team> byID = DatabaseModels.teamDB.findByID(manager.getCurrentTeamID());
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
			Optional<Team> teamByID = DatabaseModels.teamDB.findByID(teamID);
			if (teamByID.isPresent()) {
				TeamModel tm=new TeamModel(DatabaseModels.getInstance(),teamByID.get());
				System.out.println("\nTeam Details:  ");
				tm.displayClubInfo();
			}
			else {
				System.out.println("There is no team by that ID.");
				return;
			}
			List<Player> players = DatabaseModels.playerDB.findByTeamID(teamID);
			if (players.isEmpty()) {
				System.out.println("No players found for this team.");
			} else {
				System.out.println("\nTeam Players:  ");
				for (Player player:players){
					System.out.println(player);
				}

			}
		}catch (InputMismatchException e){
			System.out.println("\nPlease enter a numeric value!");
			sc.next();
		}

	}
}