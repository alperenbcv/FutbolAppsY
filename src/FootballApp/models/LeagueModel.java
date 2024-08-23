package FootballApp.models;

import FootballApp.entities.*;
import FootballApp.enums.ERegion;

import java.time.LocalDate;
import java.util.*;

public class LeagueModel {
	private static Integer leagueCounter=0;
	
	private Integer id;
	private DatabaseModels databaseModel;
	private String leagueName;
	private ERegion regionList;
	private String season;
	private Integer division;
	private List<Team> leagueTeamList;
	private List<Match> matchList;
	private LocalDate seasonStartDate;
	private LocalDate seasonEndDate;
	private Map<Integer,TeamStats> teamStanding;


	public LeagueModel(DatabaseModels databaseModel, League league) {
		this.databaseModel=databaseModel;
		this.id=league.getId();
		this.leagueName=league.getLeagueName();
		this.regionList=league.getRegion();
		this.season=league.getSeason();
		this.division=league.getDivision();
		this.leagueTeamList= DatabaseModels.teamDB.findAllByLeagueID(id);
		this.matchList=DatabaseModels.matchDB.findByLeagueID(id);
		this.seasonStartDate = LocalDate.of(2024, 8, 23);
		this.seasonEndDate = LocalDate.of(2025, 6, 1);
		this.teamStanding=new LinkedHashMap<>();
		initializeStandingTable();
	}

	public void initializeStandingTable(){
		List<TeamStats> teamStatsList = DatabaseModels.tsDB.findByLeagueID(this.id);
		teamStatsList.sort(Comparator.comparing(TeamStats::getTotalPoint)
				.thenComparing(TeamStats::getAverage)
				.thenComparing(TeamStats::getGoalScored)
				.reversed());

		int rank=1;
		for(TeamStats ts:teamStatsList){
			teamStanding.put(rank++,ts);
		}
	}

	public void displayStandingTable() {
		System.out.println("League Standings:");
		System.out.println("----------------------------------------------------------------");
		System.out.println("Rank | Team Name            | Played | Points | Avg | GF | GA ");
		System.out.println("----------------------------------------------------------------");

		int rank = 1; // Takım sıralaması için sayaç

		for (Map.Entry<Integer, TeamStats> entry : teamStanding.entrySet()) {
			TeamStats stats = entry.getValue();
			String teamName = DatabaseModels.teamDB.findByID(stats.getTeamID())
					.map(Team::getTeamName)
					.orElse("Unknown");

			// Eğer takım adı "BYE" ise, atla
			if ("BYE".equalsIgnoreCase(teamName)) {
				continue;
			}

			System.out.printf("%-4d | %-20s | %-6d | %-6d | %-3d | %-3d | %-3d \n",
					rank,
					teamName,
					stats.getGamesPlayed(),
					stats.getTotalPoint(),
					stats.getAverage(),
					stats.getGoalScored(),
					stats.getGoalConceded());

			rank++; // Her döngüde sıralamayı artır
		}

		System.out.println("----------------------------------------------------------------");
	}




	public void displayLeagueInfo() {
		System.out.println("--------------------------------------------------");
		System.out.println("League Information:");
		System.out.println("--------------------------------------------------");
		System.out.println("League ID      : " + id);
		System.out.println("League Name    : " + leagueName);
		System.out.println("Region         : " + regionList);
		System.out.println("Division       : " + division);
		System.out.println("--------------------------------------------------");
	}

	public void displayLeagueTeams(){

		System.out.println("Team List      : ");
		System.out.println("--------------------------------------------------");
		List<String> list = leagueTeamList.stream()
				.filter(team -> !team.getTeamName().equalsIgnoreCase("BYE"))
				.map(team -> team.getId() + " " + team.getTeamName())
				.toList();

		for (String teamInfo : list) {
			System.out.println(teamInfo);
		}
		System.out.println("--------------------------------------------------");
	}


	public void displayFixture() {
	    
	    System.out.println("\nFixture     : ");
	    printFixtureDetails(id);
	    System.out.println("--------------------------------------------------");
    }
	
	
	public void printFixtureDetails(Integer leagueID) {
		System.out.println("Season Start Date: " + seasonStartDate);
		System.out.println("Season End Date: " + seasonEndDate);
		System.out.println("Team Count: " + leagueTeamList.size());
		System.out.println();
		System.out.println("Matches:");
		
		Map<Integer, List<Match>> matchesByWeek = new LinkedHashMap<>();
		int matchWeek = 1;
		LocalDate currentWeekStartDate = seasonStartDate;
		
		for (Match match : getMatchList().stream().filter(match -> match.getLeagueId() == leagueID).toList()) {
			while (match.getMatchDate().isAfter(currentWeekStartDate.plusDays(6))) {
				matchWeek++;
				currentWeekStartDate = currentWeekStartDate.plusWeeks(1);
			}
			matchesByWeek.computeIfAbsent(matchWeek, k -> new ArrayList<>()).add(match);
		}
		
		for (Map.Entry<Integer, List<Match>> entry : matchesByWeek.entrySet()) {
			System.out.println("Week " + entry.getKey() + ":");
			for (Match match : entry.getValue()) {
				String homeTeamName = DatabaseModels.teamDB.findByID(match.getHomeTeamId()).map(Team::getTeamName).orElse("Unknown");
				String awayTeamName = DatabaseModels.teamDB.findByID(match.getAwayTeamId()).map(Team::getTeamName).orElse("Unknown");
				System.out.println("  " + match.getMatchDate() + ": " + homeTeamName + " vs " + awayTeamName);
			}
		}
	}
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public DatabaseModels getDatabaseModel() {
		return databaseModel;
	}
	
	public void setDatabaseModel(DatabaseModels databaseModel) {
		this.databaseModel = databaseModel;
	}
	
	public String getLeagueName() {
		return leagueName;
	}
	
	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}
	
	public ERegion getRegionList() {
		return regionList;
	}
	
	public void setRegionList(ERegion regionList) {
		this.regionList = regionList;
	}
	
	public String getSeason() {
		return season;
	}
	
	public void setSeason(String season) {
		this.season = season;
	}
	
	public Integer getDivision() {
		return division;
	}
	
	public void setDivision(Integer division) {
		this.division = division;
	}
	
	public List<Team> getLeagueTeamList() {
		return leagueTeamList;
	}
	
	public void setLeagueTeamList(List<Team> leagueTeamList) {
		this.leagueTeamList = leagueTeamList;
	}
	
	public List<Match> getMatchList() {
		return matchList;
	}
	
	public void setMatchList(List<Match> matchList) {
		this.matchList = matchList;
	}
	
	public LocalDate getSeasonStartDate() {
		return seasonStartDate;
	}
	
	public void setSeasonStartDate(LocalDate seasonStartDate) {
		this.seasonStartDate = seasonStartDate;
	}
	
	public LocalDate getSeasonEndDate() {
		return seasonEndDate;
	}
	
	public void setSeasonEndDate(LocalDate seasonEndDate) {
		this.seasonEndDate = seasonEndDate;
	}
	
	@Override
	public String toString() {
		return "LeagueModel{" + "id=" + getId() + ", leagueName='" + getLeagueName() + '\'' + ", regionList=" + getRegionList() + ", season='" + getSeason() + '\'' + ", division=" + getDivision() + ", leagueTeamList=" + getLeagueTeamList() + ", matchList=" + getMatchList() + '}';
	}
}