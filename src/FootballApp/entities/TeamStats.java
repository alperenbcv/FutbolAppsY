package FootballApp.entities;

import FootballApp.enums.EMatchStatus;
import FootballApp.models.DatabaseModels;
import FootballApp.utility.DataIO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeamStats extends BaseEntity implements Observable {
	private List<Observer> observers = new ArrayList<>();
	private static Integer tableCounter=0;


	private LocalDate lastUpdateDate;
	private Integer teamLeagueID;
	private Integer teamID;
	private Integer totalPoint;
	private Integer goalScored;
	private Integer goalConceded;
	private Integer average;
	private Integer gamesPlayed;
	private Integer gamesWon;
	private Integer gamesLost;
	private Integer gamesDrawn;


	public TeamStats(Integer teamID) {
		super(++tableCounter);
		this.teamID = teamID;
		this.teamLeagueID = leagueIDFinder();
		this.gamesDrawn = 0;
		this.gamesLost = 0;
		this.gamesWon = 0;
		this.goalConceded = 0;
		this.goalScored = 0;
		this.average = 0;
		this.gamesPlayed = 0;
		this.totalPoint = 0;
		this.lastUpdateDate = LocalDate.of(2024, 8, 22);
		DatabaseModels.tsDB.save(this);
	}

	public void teamStatUpdater(LocalDate currentDate) {
		if (currentDate.isAfter(lastUpdateDate)) {
			teamPointCalculator(currentDate);
			teamGoalCalculator(currentDate);
			teamAverageCalculator();
			calculateGamesPlayed();
			lastUpdateDate = currentDate;
			notifyObservers();
		}
	}

	public void calculateGamesPlayed() {
		List<Match> playedGames = teamsMatchGetter();
		this.gamesPlayed = playedGames.size();
	}

	public static void teamStatGenerator(){
		List<Team> teams = DatabaseModels.teamDB.listAll();
		for(Team team:teams){
			new TeamStats(team.getId());
		}
	}

	public List<Match> teamsMatchGetter(){
		List<Match> byTeamID = DatabaseModels.matchDB.findByTeamID(this.teamID);
		List<Match> playedGames = byTeamID.stream().filter(match -> match.getStatus().equals(EMatchStatus.PLAYED)).toList();
		return playedGames;
	}


	public Integer leagueIDFinder(){
		Optional<Team> byID = DatabaseModels.teamDB.findByID(this.teamID);
		Team team = byID.get();
		return team.getLeagueID();
	}

	public void teamAverageCalculator(){
		this.average=this.goalScored-this.goalConceded;
	}

	public void teamGoalCalculator(LocalDate currentDate) {
		List<Match> playedGames = teamsMatchGetter().stream()
				.filter(match -> match.getMatchDate().equals(currentDate))
				.toList();
		for (Match match : playedGames) {
			if (match.getHomeTeamId() == this.teamID) {
				this.goalScored += match.getHomeTeamScore();
				this.goalConceded += match.getAwayTeamScore();
			}
			if (match.getAwayTeamId() == this.teamID) {
				this.goalScored += match.getAwayTeamScore();
				this.goalConceded += match.getHomeTeamScore();
			}
		}
	}

	public void teamPointCalculator(LocalDate currentDate) {
		List<Match> playedGames = teamsMatchGetter().stream()
				.filter(match -> match.getMatchDate().equals(currentDate))
				.toList();
		for (Match match : playedGames) {
			if (match.getHomeTeamId() == this.teamID) {
				if (match.getHomeTeamScore() > match.getAwayTeamScore()) {
					this.totalPoint += 3;
					this.gamesWon++;
				} else if (match.getHomeTeamScore().equals(match.getAwayTeamScore())) {
					this.totalPoint++;
					this.gamesDrawn++;
				} else {
					this.gamesLost++;
				}
			}
			if (match.getAwayTeamId() == this.teamID) {
				if (match.getAwayTeamScore() > match.getHomeTeamScore()) {
					this.totalPoint += 3;
					this.gamesWon++;
				} else if (match.getAwayTeamScore().equals(match.getHomeTeamScore())) {
					this.totalPoint++;
					this.gamesDrawn++;
				} else {
					this.gamesLost++;
				}
			}
		}
	}
	
	
	public LocalDate getLastUpdateDate() {
		return lastUpdateDate;
	}
	
	public void setLastUpdateDate(LocalDate lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	
	public Integer getTeamID() {
		return teamID;
	}

	public void setTeamID(Integer teamID) {
		this.teamID = teamID;
	}

	public Integer getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(Integer totalPoint) {
		this.totalPoint = totalPoint;
	}

	public Integer getGoalScored() {
		return goalScored;
	}

	public void setGoalScored(Integer goalScored) {
		this.goalScored = goalScored;
	}

	public Integer getGoalConceded() {
		return goalConceded;
	}

	public void setGoalConceded(Integer goalConceded) {
		this.goalConceded = goalConceded;
	}

	public Integer getAverage() {
		return average;
	}

	public void setAverage(Integer average) {
		this.average = average;
	}

	public Integer getGamesPlayed() {
		return gamesPlayed;
	}

	public void setGamesPlayed(Integer gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}

	public Integer getGamesWon() {
		return gamesWon;
	}

	public void setGamesWon(Integer gamesWon) {
		this.gamesWon = gamesWon;
	}

	public Integer getGamesLost() {
		return gamesLost;
	}

	public void setGamesLost(Integer gamesLost) {
		this.gamesLost = gamesLost;
	}

	public Integer getGamesDrawn() {
		return gamesDrawn;
	}

	public void setGamesDrawn(Integer gamesDrawn) {
		this.gamesDrawn = gamesDrawn;
	}

	public Integer getTeamLeagueID() {
		return teamLeagueID;
	}

	public void setTeamLeagueID(Integer teamLeagueID) {
		this.teamLeagueID = teamLeagueID;
	}
	
	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
	}
	
	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}
	
	@Override
	public void notifyObservers() {
		for (Observer observer : observers) {
			DataIO.getInstance().update(this);
		}
	}
}