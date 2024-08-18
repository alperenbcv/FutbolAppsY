package FootballApp.modules;

import FootballApp.databases.PlayerDB;
import FootballApp.entities.Player;
import FootballApp.entities.Team;
import FootballApp.utility.DataGenerator;
import FootballApp.utility.DataIO;

import javax.xml.crypto.Data;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class PlayerModule {
	static Scanner sc = new Scanner(System.in);
	
	public static void playerMenu() {
		int userInput;
		do {
			userInput = playerMenuEntry();
			playerMenuSelection(userInput);
		} while (userInput != 0);
	}
	
	private static int playerMenuEntry() {
		int userInput = -1;
		boolean validInput = false;
		
		while (!validInput) {
			System.out.println("1-List of all Player ID's by Team");
			System.out.println("2-Find Player by ID");
			System.out.println("3-Find Player by Name");
			System.out.println("4-Find Player by Team");
			System.out.println("0-Main Menu");
			System.out.print("Selection: ");
			try {
				userInput = sc.nextInt();
				sc.nextLine();
				if (userInput >= 0 && userInput <= 4) {
					validInput = true;
				} else {
					System.out.println("Please enter a valid option!");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nPlease enter a numeric value!");
				sc.next();
			}
		}
		return userInput;
	}
	
	private static void playerMenuSelection(int userInput) {
		switch (userInput) {
			case 1 -> displayPlayersByTeam();
			case 2 -> displayPlayerByID();
			case 3 -> displayPlayerByName();
			case 4 -> displayPlayersByTeamName();
			case 0 -> System.out.println("Returning to Main Menu...");
			default -> System.out.println("Please enter a valid value!");
		}
	}
	
	private static void displayPlayersByTeam() {
		System.out.println("List of all Player ID's by Team");
		List<Team> teams = DataIO.teamDB.listAll();
		teams.forEach(team -> {
			System.out.println("Team ID: " + team.getId() + ", Team Name: " + team.getTeamName());
			System.out.println("Team Players ID List: " + team.getTeamPlayerIDList());
		});
		displayPlayerDetails();
	}
	
	private static void displayPlayerByID() {
		int size = DataIO.playerDB.listAll().size();
		System.out.println("Enter a Player ID 1-"+size+": 0=Back to Player Menu");
		Integer playerID = sc.nextInt();
		sc.nextLine();
		if (playerID == 0) {
			return;
		}
		Optional<Player> playerByID = DataIO.playerDB.findByID(playerID);
		if (playerByID.isPresent()) {
			System.out.println(playerByID.get());
		} else {
			System.out.println("Player not found!");
		}
	}
	
	private static void displayPlayerByName() {
		System.out.println("Enter a Player Name: 0=Back to Player Menu");
		String playerName = sc.nextLine();
		if (playerName.equalsIgnoreCase("0")) {
			return;
		}
		List<Player> byPlayerName = DataIO.playerDB.findByPlayerName(playerName);
		if (byPlayerName.isEmpty()) {
			System.out.println("Player not found!");
			return;
		}
		byPlayerName.forEach(player -> System.out.println("PlayerID: " + player.getId() + " PlayerNameSurname: " + player.getName() + " " + player.getSurName()));
		displayPlayerDetails();
	}
	
	private static void displayPlayersByTeamName() {
		System.out.println("Enter a Team Name: 0=Back to Player Menu");
		String teamName = sc.nextLine();
		if (teamName.equalsIgnoreCase("0")) {
			return;
		}
		List<Player> byTeamName = DataIO.playerDB.findByTeamName(teamName);
		if (byTeamName.isEmpty()) {
			return;
		}
		
		byTeamName.forEach(System.out::println);
	}
	
	private static void displayPlayerDetails() {
		System.out.println("Which player do you want to select? Please enter the Player ID: 0=Back to Player Menu");
		Integer playerID = sc.nextInt();
		sc.nextLine(); // consume newline
		if (playerID == 0) {
			return;
		}
		Optional<Player> player = DataIO.playerDB.findByID(playerID);
		if (player.isPresent()) {
			System.out.println("Player Details: ");
			System.out.println(player.get());
		} else {
			System.out.println("Player not found!");
		}
	}
}