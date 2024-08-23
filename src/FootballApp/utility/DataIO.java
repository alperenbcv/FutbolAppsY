package FootballApp.utility;

import FootballApp.entities.*;
import FootballApp.entities.attributes.TechnicalAttributes;
import FootballApp.enums.EMatchStatus;
import FootballApp.enums.EPosition;
import FootballApp.enums.ERegion;
import FootballApp.models.DatabaseModels;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DataIO{
	
	static File file = new File("teams.txt");
	static File file2 = new File("managers.txt");
	static File file3 = new File("players.txt");
	static File file4 = new File("matches.txt");
	static File file5 = new File("leagues.txt");
	static FixtureGenerator fg = new FixtureGenerator();
	
	public static void dataIOInitialize() {
		generateLeagues();
		generateTeams();
		setTeamsToLeague();
		generateManagers();
		generatePlayers();
		readMatches();
		initializeObservers();
		TeamStats.teamStatGenerator();
		
//	    	generateFixtures();
//			savePlayersToFile();
//			saveTeamsToFile();
//			saveManagersToFile();
//			saveLeaguesToFile();
//			saveMatchesToFile();
		
	
		
		
		
		System.out.println("Total loaded entities: " + totalEntities());
		}
	
	
	private static void initializeObservers() {
		ObserverInitializer observerInitializer = new ObserverInitializer(DatabaseModels.getInstance());
		
		observerInitializer.initializeObservers();
	}
		
		
		public static int totalEntities() {
		int totalSize;
			int size1 = DatabaseModels.matchDB.listAll().size();
			int size2 = DatabaseModels.teamDB.listAll().size();
			int size3 = DatabaseModels.playerDB.listAll().size();
			int size4 = DatabaseModels.managerDB.listAll().size();
			int size5 = DatabaseModels.leagueDB.listAll().size();
			totalSize=size1 + size2 + size3 + size4 + size5;
			return totalSize;
		}
		
		
	public static void saveMatchesToFile() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("matches.txt"))) {
			for (Match match : DatabaseModels.matchDB.listAll()) {
				writer.write(match.getHomeTeamId() + ","
						             + match.getAwayTeamId() + ","
						             + match.getMatchDate() + ","
						             + match.getStatus() + ","
						             + match.getLeagueId() + ","
						             + match.getHomeTeamScore() + ","
						             + match.getAwayTeamScore() + ","
						             + "\n");
			}
		} catch (IOException e) {
			System.err.println("Error while writing matches.txt: " + e.getMessage());
		}
	}
	
	public static void readMatches() {
		
		try (Scanner sc = new Scanner(new FileReader("matches.txt"))) {
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] split = line.split(",");
				
				Integer homeTeamId = Integer.parseInt(split[0]);
				Integer awayTeamId = Integer.parseInt(split[1]);
				LocalDate matchDate = LocalDate.parse(split[2]);
				EMatchStatus matchStatus = EMatchStatus.valueOf(split[3]);
				Integer leagueID = Integer.parseInt(split[4]);
				
				new Match(homeTeamId, awayTeamId, matchDate, matchStatus, leagueID);
				
			}
			
		} catch (FileNotFoundException e) {
			System.err.println("matches.txt not found: " + e.getMessage());
		} catch (IOException | NumberFormatException e) {
			System.err.println("Error parsing match data: " + e.getMessage());
		}
	}

	
	private static void generateFixtures() {
		List<League> leagues = DatabaseModels.leagueDB.listAll();
		List<Integer> leagueIDList = leagues.stream().map(league -> league.getId()).toList();
		for (int i = 0; i < leagueIDList.size(); i++) {
			fg.generateFixtures(leagueIDList.get(i));
		}
	}
	
	
	private static void setTeamsToLeague() {
		List<League> leagues = DatabaseModels.leagueDB.listAll();
		List<Team> teams = DatabaseModels.teamDB.listAll();
		for (League league : leagues) {
			int id = league.getId();
			for (Team team : teams) {
				if(team.getLeagueID() == id){
					league.setLeagueTeamIDList(team.getId());
				}
			}
		}
	}
	
	
	
	private static void generateLeagues() {
		try (Scanner sc = new Scanner(new FileReader("leagues.txt"))) {
			while (sc.hasNextLine()) {
				String satir = sc.nextLine();
				String[] split = satir.split(",");
				
				new League(split[0] ,Integer.parseInt(split[1]), split[2],
						           Integer.parseInt(split[3]), ERegion.valueOf(split[4]));
				
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
			System.err.println("Error parsing league data: " + e.getMessage());
		}
	}
	
	public static void saveLeaguesToFile() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("leagues.txt"))) {
			for (League league : DatabaseModels.leagueDB.listAll()) {
				writer.write(league.getLeagueName() + ","
						             + league.getLeagueStandingTableID() + ","
						             + league.getSeason() + ","
						             + league.getDivision() + ","
						             + league.getRegion() + "," + "\n");
			}
		}
		catch (IOException e) {
			System.err.println("Error while writing leagues.txt: " + e.getMessage());
		}
	}
	
	
	public static void saveTeamsToFile() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("teams.txt"))) {
			for (Team team : DatabaseModels.teamDB.listAll()) {
				writer.write(team.getLeagueID()+","
						             +team.getTeamName()+ ","
						             + team.getTeamLocation() + ","
						             + team.getStadiumName() + ","
						             + team.getTransferBudget() + ","
						             + team.getWageBudget() + "," + "\n");
			}
		}
		catch (IOException e) {
			System.err.println("Error while writing teams.txt: " + e.getMessage());
		}
	}
	
	public static void saveManagersToFile() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("managers.txt"))) {
			for (Manager manager : DatabaseModels.managerDB.listAll()) {
				writer.write(manager.getCurrentTeamID() + "," + manager.getName() + "," + manager.getSurName() + "," + manager.getAge() + "," + manager.getNationality() + "," + manager.getManagerUserName() + "," + manager.getManagerPassword() + "," + "\n");
				
			}
		}
		catch (IOException e) {
			System.err.println("Error while writing teams.txt: " + e.getMessage());
		}
	}
	
	public static void savePlayersToFile() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("players.txt"))) {
			for (Player player : DatabaseModels.playerDB.listAll()) {
				TechnicalAttributes ta = player.getPlayerTechnicalAttributes();
				writer.write(player.getName() + "," + player.getSurName() + "," + player.getAge() + "," + player.getNationality() + "," + ta.getFinishing() + "," + ta.getPass() + "," + ta.getDribbling() + "," + ta.getTackle() + "," + ta.getShotPower() + "," + player.getCurrentTeamID() + "," + player.getPlayerValue() + "," + player.getPlayerWage() + "," + player.getPlayersPosition() + "," + "\n");
			}
		}
		catch (IOException e) {
			System.err.println("Error while writing players.txt: " + e.getMessage());
		}
	}
	
	public static void generatePlayers() {
		try (Scanner sc = new Scanner(new FileReader("players.txt"))) {
			while (sc.hasNextLine()) {
				String satir = sc.nextLine();
				String[] split = satir.split(",");
				
				TechnicalAttributes ta =
						new TechnicalAttributes(Integer.parseInt(split[4]), Integer.parseInt(split[5]),
						                        Integer.parseInt(split[6]), Integer.parseInt(split[7]),
						                        Integer.parseInt(split[8]));
				
				
						new Player(split[0], split[1], Integer.parseInt(split[2]), split[3], ta,
						           Integer.parseInt(split[9]), Double.parseDouble(split[10]),
						           Double.parseDouble(split[11]), EPosition.valueOf(split[12]));
			}
			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
			System.err.println("Error parsing player data: " + e.getMessage());
		}
	}
	
	public static void generateTeams() {
		
		try (Scanner sc = new Scanner(new FileReader("teams.txt"))) {
			while (sc.hasNextLine()) {
				String satir = sc.nextLine().trim();
				String[] split = satir.split(",");
				
				try {
					Integer leagueID = Integer.parseInt(split[0]);
					String teamName = split[1];
					String city = split[2];
					String stadiumName = split[3];
					
					double transferBudget = Double.parseDouble(split[4]);
					double wageBudget = Double.parseDouble(split[5]);
					
					new Team(leagueID, teamName, city, stadiumName, transferBudget, wageBudget);
					
				}
				catch (NumberFormatException e) {
					System.err.println("Error parsing number from line: " + satir);
				}
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
			System.err.println("Error parsing team data: " + e.getMessage());
		}
	}
	
	
	public static void generateManagers() {
		try (Scanner sc = new Scanner(new FileReader("managers.txt"))) {
			while (sc.hasNextLine()) {
				String satir = sc.nextLine();
				String[] split = satir.split(",");
				
				
				Manager manager =
						new Manager(Integer.parseInt(split[0]), split[1], split[2], Integer.parseInt(split[3]),
						            split[4], split[5], split[6]);
				
			}
			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
			System.err.println("Error parsing manager data: " + e.getMessage());
		}
	}

}