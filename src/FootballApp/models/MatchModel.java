package FootballApp.models;

import FootballApp.entities.League;
import FootballApp.entities.Match;
import FootballApp.entities.Team;
import FootballApp.enums.EMatchStatus;

import java.time.LocalDate;

public class MatchModel {
	
	
	private Team homeTeam;
	private Team awayTeam;
	private LocalDate matchDate;
	private EMatchStatus status;
	private League matchLeague;
	private Integer homeTeamScore;
	private Integer awayTeamScore;
	private DatabaseModels databaseModel;
	
	public MatchModel(DatabaseModels dm, Match match) {
		this.databaseModel=dm;
		this.homeTeam=DatabaseModels.teamDB.findByID(match.getHomeTeamId()).get();
		this.awayTeam=DatabaseModels.teamDB.findByID(match.getAwayTeamId()).get();
		this.matchDate=match.getMatchDate();
		this.status=match.getStatus();
		this.matchLeague=DatabaseModels.leagueDB.findByID(match.getLeagueId()).get();
		this.homeTeamScore=match.getHomeTeamScore();
		this.awayTeamScore=match.getAwayTeamScore();
	}
	
	public void displayScheduledMatchInfo() {
		System.out.println("--------------------------------------------------");
		System.out.println("Match Information:");
		System.out.println("--------------------------------------------------");
		System.out.println("League         : " + matchLeague.getLeagueName());
		System.out.println("Home Team      : " + homeTeam.getTeamName());
		System.out.println("Away Team      : " + awayTeam.getTeamName());
		System.out.println("Match Date     : " + matchDate);
		System.out.println("Status         : " + status);
		System.out.println("--------------------------------------------------");
	}
	
	public void displayPlayedMatchInfo() {
		System.out.println("--------------------------------------------------");
		System.out.println("Match Information:");
		System.out.println("--------------------------------------------------");
		System.out.println("League               : " + matchLeague.getLeagueName());
		System.out.println("Home Team            : " + homeTeam.getTeamName());
		System.out.println("Home Team Score      : " + homeTeamScore);
		System.out.println("Away Team            : " + awayTeam.getTeamName());
		System.out.println("Away Team Score      : " + awayTeamScore);
		System.out.println("Match Date           : " + matchDate);
		System.out.println("Status               : " + status);
		System.out.println("--------------------------------------------------");
	}
}