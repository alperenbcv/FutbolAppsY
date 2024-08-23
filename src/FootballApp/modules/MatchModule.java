package FootballApp.modules;

import FootballApp.entities.Match;
import FootballApp.enums.EMatchStatus;
import FootballApp.models.DatabaseModels;
import FootballApp.models.MatchModel;

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
			System.out.println("3-Display Results by Date");
			System.out.println("4-Display Yesterday Results");
			System.out.println("5-Skip Day");
			System.out.println("0-Main Menu");
			System.out.print("Selection: ");
			try {
				userInput = sc.nextInt();
				sc.nextLine();
				if(userInput >= 0 && userInput <= 5) {
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
			case 3 -> displayResults();
			case 4 -> displayTodayResults();
			case 5 -> skipDay();
			case 0 -> System.out.println("\nReturning to Main Menu...\n");
			default-> System.out.println("Please enter a valid value!");
		}
	}
	
	private static void displayTodayResults() {
		List<Match> matchesOfTheDay =
				DatabaseModels.matchDB.listAll().stream().filter(match -> match.getMatchDate().equals(gameLocalDate.minusDays(1)))
				                      .collect(Collectors.toList());
		for (Match match : matchesOfTheDay) {
			MatchModel mm = new MatchModel(DatabaseModels.getInstance(), match);
			mm.displayPlayedMatchInfo();
		}
	}
	
	private static void displayResults() {
		System.out.println("Enter a date to display match results (YYYY-MM-DD): ");
		LocalDate selectedDate = LocalDate.parse(sc.nextLine());
		List<Match> matchesOfTheDay =
				DatabaseModels.matchDB.listAll().stream().filter(match -> match.getMatchDate().equals(selectedDate))
				                      .collect(Collectors.toList());
		if (matchesOfTheDay.isEmpty()) {
			System.out.println("No matches played for the day!");
		}
		else {
			for (Match match : matchesOfTheDay) {
				MatchModel mm = new MatchModel(DatabaseModels.getInstance(), match);
				mm.displayPlayedMatchInfo();
			}
		}
	}
	
	private static void skipDay() {
		simulateGames();
		gameLocalDate = gameLocalDate.plusDays(1);
		
		System.out.println("Day skipped to: " + gameLocalDate);
	}
	
	private static void simulateGames() {
		List<Match> matchesOfTheDay =
				DatabaseModels.matchDB.listAll().stream().filter(match -> match.getMatchDate().equals(gameLocalDate))
				                      .collect(Collectors.toList());
		
		if (matchesOfTheDay.isEmpty()) {
			System.out.println("No matches scheduled for today!");
		}
		else {
			Random random = new Random();
			
			for(Match match:matchesOfTheDay){
				if(match.getHomeTeamId() == DatabaseModels.teamDB.findByName("BYE").get().getId()){
					match.setHomeTeamScore(0);
					match.setAwayTeamScore(3);
				}
				else if(match.getAwayTeamId() == DatabaseModels.teamDB.findByName("BYE").get().getId()){
                    match.setHomeTeamScore(3);
                    match.setAwayTeamScore(0);
                }
				else {
					int homeTeamScore = random.nextInt(4);
					int awayTeamScore = random.nextInt(4);
					match.setHomeTeamScore(homeTeamScore);
					match.setAwayTeamScore(awayTeamScore);
					match.setStatus(EMatchStatus.PLAYED);
				}
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
			for (Match match:matchesOfTheDay){
				MatchModel mm = new MatchModel(DatabaseModels.getInstance(),match);
				mm.displayScheduledMatchInfo();
			}
        
        }
	}
}