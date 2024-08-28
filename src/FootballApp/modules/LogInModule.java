package FootballApp.modules;

import FootballApp.entities.League;
import FootballApp.entities.Manager;
import FootballApp.entities.Team;
import FootballApp.models.DatabaseModels;
import FootballApp.utility.DataIO;
import FootballApp.utility.FixtureGenerator;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class LogInModule {
	static Scanner sc = new Scanner(System.in);
	static Manager loggedManager = null;
	
	public static Manager managerLogIn() {
		System.out.println("\nWelcome to the Football Manager App!");
		loadGame();
		System.out.println("\nPlease choose a league first!");
		System.out.println("\n---------------Available Leagues------------------");
		LeagueModule.displayAllLeagues();
		
		Integer leagueID = null;
		boolean validLeagueID = false;
		
		do {
			try {
				System.out.print("Please Enter a League ID: ");
				leagueID = sc.nextInt();
				sc.nextLine();
				Optional<League> league = DatabaseModels.leagueDB.findByID(leagueID);
				if (league.isPresent()) {
					validLeagueID = true;
				} else {
					System.out.println("Please enter a valid League ID.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Enter a numeric value!");
				sc.nextLine();
			}
		} while (!validLeagueID);
		
		System.out.println("\n---------------Available Managers in The League------------------");
		List<Manager> all = DatabaseModels.managerDB.findByLeagueID(leagueID);
		all.stream().forEach(manager -> {
			Optional<Team> byID = DatabaseModels.teamDB.findByID(manager.getCurrentTeamID());
			if (byID.isPresent()) {
				System.out.println(manager.getId() + " " + manager.getName() + " " + manager.getSurName() + " " + byID.get().getTeamName());
			}
		});
		
		boolean validInput = false;
		do {
			System.out.print("Enter a Manager ID to see login information: (0 to Menu): ");
			Optional<Integer> managerID = Optional.empty();
			
			try {
				managerID = Optional.of(sc.nextInt());
			} catch (InputMismatchException e) {
				System.out.println("Please enter a numeric value!");
				sc.nextLine();
				continue;
			}
			
			sc.nextLine();
			
			if (managerID.isPresent()) {
				int id = managerID.get();
				if (id == 0) {
					return null;
				}
				if (id > 0 && id <= DatabaseModels.managerDB.listAll().size()) {
					Optional<Manager> byID = DatabaseModels.managerDB.findByID(id);
					if (byID.isPresent()) {
						System.out.println("\n------------------------------------------------");
						System.out.println("Username: " + byID.get().getManagerUserName() + ", Password: " + byID.get().getManagerPassword());
						validInput = true;
						System.out.println("------------------------------------------------");
					} else {
						System.out.println("Manager not found! Please enter a valid ID.");
					}
				} else {
					System.out.println("Invalid ID. Please enter a value within the valid range.");
				}
			}
		} while (!validInput);
		
		do {
			System.out.print("\nEnter your Username: ");
			String username = sc.nextLine();
			System.out.print("Enter your Password: ");
			String password = sc.nextLine();
			
			Optional<Manager> byUsernameAndPassword = DatabaseModels.managerDB.findByUsernameAndPassword(username, password);
			if (byUsernameAndPassword.isPresent()) {
				System.out.println("Login Successful!");
				System.out.println("\nYou are playing as: " + byUsernameAndPassword.get().getName() + " " + byUsernameAndPassword.get().getSurName() + "\n");
				loggedManager = byUsernameAndPassword.get();
				return loggedManager;
			} else {
				System.out.println("\nInvalid credentials. Please try again.");
			}
		} while (true);
	}

	public static Manager managerLogOut(){
		loggedManager=null;
		System.out.println("You are logged out!");
		return loggedManager;
	}
	
	public static void loadGame() {
		System.out.println("\nDo you want to load your last session? (y/n): ");
		String s = sc.nextLine();
		if(s.equalsIgnoreCase("y")) {
			DataIO.generateLeagues();
			DataIO.generateTeams();
			DataIO.setTeamsToLeague();
			DataIO.generateManagers();
//			DataIO.generatePlayers();
			DataIO.readMatches();
			DataIO.readTeamStats();
			MatchModule.readDate();
		}
        else if(s.equalsIgnoreCase("n")) {
			DataIO.generateLeagues();
			DataIO.generateTeams();
			DataIO.setTeamsToLeague();
			DataIO.generateManagers();
//			DataIO.generatePlayers();
			DataIO.readMatchesNoSave();
			DataIO.readTeamStatsNoSave();
			MatchModule.unsavedDate();
			}
            
            System.out.println("\nGame loaded successfully!");
		}
}