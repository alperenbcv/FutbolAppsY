package FootballApp.utility;

import FootballApp.entities.*;
import FootballApp.entities.Observable;
import FootballApp.entities.Observer;
import FootballApp.entities.attributes.TechnicalAttributes;
import FootballApp.enums.EMatchStatus;
import FootballApp.enums.EPosition;
import FootballApp.enums.ERegion;
import FootballApp.models.DatabaseModels;
import FootballApp.modules.MatchModule;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DataIO implements Observer {
	
	static File file = new File("teams.txt");
	static File file2 = new File("managers.txt");
	static File file3 = new File("players.txt");
	static File file4 = new File("matches.txt");
	static File file5 = new File("leagues.txt");
	static FixtureGenerator fg = new FixtureGenerator();
	private static DataIO instance = new DataIO();
	
	public static DataIO getInstance() {
		return instance;
	}
	
	public static void dataIOInitialize() {
		
//		MatchModule.loadDateFromFile();
		generateLeagues();
		generateTeams();
		setTeamsToLeague();
		generateManagers();
		generatePlayers();
		readMatches();
		readTeamStats();
//		TeamStats.teamStatGenerator();
//		saveTeamStatsToFile();
		initializeObservers();
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
			int size6 = DatabaseModels.tsDB.findAll().get().size();
			totalSize=size1 + size2 + size3 + size4 + size5 + size6;
			return totalSize;
		}
	
	
	
	public static void saveTeamStatsToFile() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("ts.txt"))) {
			for (TeamStats teamStats : DatabaseModels.tsDB.findAll().get()) {
				writer.write(teamStats.getTeamID() + ","
						             + teamStats.getAverage() + ","
						             + teamStats.getTeamLeagueID() + ","
						             + teamStats.getGamesPlayed() + ","
						             + teamStats.getGamesDrawn() + ","
						             + teamStats.getGamesWon() + ","
						             + teamStats.getGamesLost() + ","
						             + teamStats.getGoalScored() + ","
						             + teamStats.getGoalConceded() + ","
									 + teamStats.getTotalPoint() + ","
									 + teamStats.getLastUpdateDate() + ","
						             + "\n");
			}
		} catch (IOException e) {
			System.err.println("Error while writing matches.txt: " + e.getMessage());
		}
	}
	
	public static void readTeamStats() {
		
		try (Scanner sc = new Scanner(new FileReader("ts.txt"))) {
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] split = line.split(",");
				
				Integer teamID = Integer.parseInt(split[0]);
				Integer average = Integer.parseInt(split[1]);
				Integer leagueID = Integer.parseInt(split[2]);
				Integer gamesPlayed = Integer.parseInt(split[3]);
				Integer gamesDrawn = Integer.parseInt(split[4]);
				Integer gamesWon = Integer.parseInt(split[5]);
				Integer gamesLost = Integer.parseInt(split[6]);
				Integer goalScored = Integer.parseInt(split[7]);
				Integer goalConceded = Integer.parseInt(split[8]);
				Integer totalPoint = Integer.parseInt(split[9]);
				LocalDate lastUpdateDate = LocalDate.parse(split[10]);
				
				
				TeamStats ts=new TeamStats(teamID);
				ts.setAverage(average);
				ts.setTeamLeagueID(leagueID);
				ts.setGamesDrawn(gamesDrawn);
				ts.setGamesLost(gamesLost);
				ts.setGamesPlayed(gamesPlayed);
				ts.setGamesWon(gamesWon);
				ts.setGoalConceded(goalConceded);
				ts.setGoalScored(goalScored);
				ts.setTotalPoint(totalPoint);
				ts.setLastUpdateDate(lastUpdateDate);
				
			}
			
		} catch (FileNotFoundException e) {
			System.err.println("matches.txt not found: " + e.getMessage());
		} catch (IOException | NumberFormatException e) {
			System.err.println("Error parsing match data: " + e.getMessage());
		}
	}
	
	public static void readTeamStatsNoSave() {
		
		try (Scanner sc = new Scanner(new FileReader("ts2.txt"))) {
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] split = line.split(",");
				
				Integer teamID = Integer.parseInt(split[0]);
				Integer average = Integer.parseInt(split[1]);
				Integer leagueID = Integer.parseInt(split[2]);
				Integer gamesPlayed = Integer.parseInt(split[3]);
				Integer gamesDrawn = Integer.parseInt(split[4]);
				Integer gamesWon = Integer.parseInt(split[5]);
				Integer gamesLost = Integer.parseInt(split[6]);
				Integer goalScored = Integer.parseInt(split[7]);
				Integer goalConceded = Integer.parseInt(split[8]);
				Integer totalPoint = Integer.parseInt(split[9]);
				LocalDate lastUpdateDate = LocalDate.parse(split[10]);
				
				
				TeamStats ts=new TeamStats(teamID);
				ts.setAverage(average);
				ts.setTeamLeagueID(leagueID);
				ts.setGamesDrawn(gamesDrawn);
				ts.setGamesLost(gamesLost);
				ts.setGamesPlayed(gamesPlayed);
				ts.setGamesWon(gamesWon);
				ts.setGoalConceded(goalConceded);
				ts.setGoalScored(goalScored);
				ts.setTotalPoint(totalPoint);
				ts.setLastUpdateDate(lastUpdateDate);
				
			}
			
		} catch (FileNotFoundException e) {
			System.err.println("matches.txt not found: " + e.getMessage());
		} catch (IOException | NumberFormatException e) {
			System.err.println("Error parsing match data: " + e.getMessage());
		}
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
	
	public static void readMatchesNoSave() {
		
		try (Scanner sc = new Scanner(new FileReader("matches2.txt"))) {
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
	
	
	public static void setTeamsToLeague() {
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
	
	
	
	public static void generateLeagues() {
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
	
	@Override
	public void update(Observable observable) {
		saveMatchesToFile();
		saveTeamsToFile();
		saveManagersToFile();
		savePlayersToFile();
		saveLeaguesToFile();
		saveTeamStatsToFile();
		
	}
	
	
	public void save() {
		saveMatchesToFile();
		saveTeamsToFile();
		saveManagersToFile();
		savePlayersToFile();
		saveLeaguesToFile();
		saveTeamStatsToFile();
		
	}
}