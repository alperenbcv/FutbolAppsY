package FootballApp.modules;

import FootballApp.entities.Match;
import FootballApp.enums.EMatchStatus;
import FootballApp.models.DatabaseModels;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MatchModule {
	static Scanner sc = new Scanner(System.in);
	private static LocalDate gameLocalDate = LocalDate.of(2024, 8, 23);
	
	public static void startMatchMenu() {
		int userInput;
		do {
			userInput = matchMenuEntry();
			matchMenuSelection(userInput);
		} while (userInput != 0);
	}
	
	private static int matchMenuEntry() {
		int userInput=-1;
		boolean validInput = false;
		
		while(!validInput) {
			System.out.println("\n---------Match Menu--------------");
			System.out.println("1-Matches of the Day");
			System.out.println("2-Display Date");
			System.out.println("3-Match Results");
			System.out.println("4-Skip Day");
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
	
	private static void matchMenuSelection(int userInput) {
		switch (userInput) {
			case 1 -> displayTodaysGame();
			case 2 -> displayCurrentDate();
			case 3 -> simulateGames();
			case 4 -> skipDay();
			case 0 -> System.out.println("\nReturning to Main Menu...\n");
			default-> System.out.println("Please enter a valid value!");
		}
	}
	
	private static void skipDay() {
		gameLocalDate = gameLocalDate.plusDays(1);
		System.out.println("Day skipped to: " + gameLocalDate);
	}
	
	private static void simulateGames() {
		System.out.println("Matches of the Day (" + gameLocalDate + "):");
		List<Match> matchesOfTheDay =
				DatabaseModels.matchDB.listAll().stream().filter(match -> match.getMatchDate().equals(gameLocalDate))
				                      .collect(Collectors.toList());
		
		if (matchesOfTheDay.isEmpty()) {
			System.out.println("No matches scheduled for today!");
		}
		else {
			Random random = new Random();
			
			for(Match match:matchesOfTheDay){
				int homeTeamScore = random.nextInt(4);
				int awayTeamScore = random.nextInt(4);
				match.setHomeTeamScore(homeTeamScore);
				match.setAwayTeamScore(awayTeamScore);
				match.setStatus(EMatchStatus.PLAYED);
				match.notifyObservers();
				System.out.println("Match result: " + match.getHomeTeamId() + "-" + match.getHomeTeamScore() + " vs " + match.getAwayTeamId() + "-" + match.getAwayTeamScore());
			}
		}
	}
	
	private static void displayCurrentDate() {
		System.out.println("Current Game Date: " + gameLocalDate);
	}
	
	private static void displayTodaysGame() {
		System.out.println("Matches of the Day (" + gameLocalDate + "):");
		List<Match> matchesOfTheDay =
				DatabaseModels.matchDB.listAll().stream().filter(match -> match.getMatchDate().equals(gameLocalDate))
				                      .collect(Collectors.toList());
		
		if(matchesOfTheDay.isEmpty()) {
			System.out.println("No matches scheduled for today!");
		}
		else {
            matchesOfTheDay.forEach(System.out::println);
        }
	}
}