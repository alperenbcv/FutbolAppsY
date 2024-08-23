package FootballApp.models;

import FootballApp.entities.*;
import FootballApp.enums.EMatchStatus;

import java.time.LocalDate;
import java.util.*;

public class TeamModel {
	
	private Integer teamID;
	private String teamName;
	private String teamLocation;
	private Double transferBudget;
	private Double wageBudget;
	private String stadiumName;
	private List<Match> teamsMatches;
	private List<Player> teamPlayers;
	private DatabaseModels databaseModels;
	
	public TeamModel(DatabaseModels databaseModels, Team team) {
		this.databaseModels = databaseModels;
		this.teamID = team.getId();
		this.teamName = team.getTeamName();
		this.teamLocation = team.getTeamLocation();
		this.transferBudget = team.getTransferBudget();
		this.wageBudget = team.getWageBudget();
		this.stadiumName = team.getStadiumName();
		this.teamsMatches = DatabaseModels.matchDB.findByTeamID(team.getId());
		this.teamPlayers = DatabaseModels.playerDB.findByTeamID(team.getId());
	}
	
	public void displayConcludedMatchesofaTeam() {
		System.out.println("Played Matches of the Team: " + this.teamName);
		System.out.println("--------------------------------------------------");
		
		teamsMatches.sort(Comparator.comparing(Match::getMatchDate));
		for (Match match : teamsMatches) {
			
			if (match.getStatus() == EMatchStatus.PLAYED) {
				
				String homeTeamName = DatabaseModels.teamDB.findByID(match.getHomeTeamId())
				                                           .map(Team::getTeamName)
				                                           .orElse("Unknown");
				String awayTeamName = DatabaseModels.teamDB.findByID(match.getAwayTeamId())
				                                           .map(Team::getTeamName)
				                                           .orElse("Unknown");
				
				
				System.out.println("Date: " + match.getMatchDate() + " | " + homeTeamName +" "+ match.getHomeTeamScore() +" - "+ match.getAwayTeamScore() +" "+ awayTeamName);
			}
		}
		
		System.out.println("--------------------------------------------------");
	}
	
	
	
	public void displayClubInfo() {
		System.out.println("--------------------------------------------------");
		System.out.println("Team Information:");
		System.out.println("--------------------------------------------------");
		System.out.println("Team ID     	 : " + teamID);
		System.out.println("Team Name    	 : " + teamName);
		System.out.println("Location     	 : " + teamLocation);
		System.out.println("Transfer Budget  : " + transferBudget);
		System.out.println("Wage Budget      : " + wageBudget);
		System.out.println("Stadium   	     : " + stadiumName);
		System.out.println("--------------------------------------------------");
	}

	public void displayTeamPlayers(){
			System.out.println("Players     : ");
			System.out.println("--------------------------------------------------");
			if (teamPlayers != null && !teamPlayers.isEmpty()) {
				teamPlayers.forEach(player -> new PlayerModel(databaseModels,player).displayPlayerInfo());
			} else {
				System.out.println("No players found for this club.");
			}
			System.out.println("\n--------------------------------------------------");
		}

	public Integer getTeamID() {
		return teamID;
	}
	
	public void setTeamID(Integer teamID) {
		this.teamID = teamID;
	}
	
	public String getTeamName() {
		return teamName;
	}
	
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	public String getTeamLocation() {
		return teamLocation;
	}
	
	public void setTeamLocation(String teamLocation) {
		this.teamLocation = teamLocation;
	}
	
	public Double getTransferBudget() {
		return transferBudget;
	}
	
	public void setTransferBudget(Double transferBudget) {
		this.transferBudget = transferBudget;
	}
	
	public Double getWageBudget() {
		return wageBudget;
	}
	
	public void setWageBudget(Double wageBudget) {
		this.wageBudget = wageBudget;
	}
	
	public String getStadiumName() {
		return stadiumName;
	}
	
	public void setStadiumName(String stadiumName) {
		this.stadiumName = stadiumName;
	}
	
	
	public List<Match> getTeamsMatches() {
		return teamsMatches;
	}
	
	public void setTeamsMatches(List<Match> teamsMatches) {
		this.teamsMatches = teamsMatches;
	}
	
	public List<Player> getTeamPlayers() {
		return teamPlayers;
	}
	
	public void setTeamPlayers(List<Player> teamPlayers) {
		this.teamPlayers = teamPlayers;
	}
	
}