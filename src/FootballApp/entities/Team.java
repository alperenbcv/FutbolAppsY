package FootballApp.entities;

import FootballApp.models.DatabaseModels;

import java.util.ArrayList;
import java.util.List;

public class Team extends BaseEntity implements Observable {
	private List<Observer> observers = new ArrayList<>();
	
	private static Integer teamCounter=0;
	private String teamName;
	private String teamLocation;
	private Double transferBudget;
	private Double wageBudget;
	private String stadiumName;
	private Integer leagueID;
	
	public Team() {
		super(++teamCounter);
	}
	
	public Team(Integer LeagueID,String teamName, String teamLocation,
	            String stadiumName, Double transferBudget, Double wageBudget) {
		super(++teamCounter);
		this.leagueID = LeagueID;
		this.teamName = teamName;
		this.teamLocation = teamLocation;
		this.stadiumName = stadiumName;
		this.transferBudget = transferBudget;
		this.wageBudget = wageBudget;
		DatabaseModels.teamDB.save(this);
	}
	
	public String getTeamName() {
		return teamName;
	}
	
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	public void setTeamLocation(String teamLocation) {
		this.teamLocation = teamLocation;
	}
	
	public void setStadiumName(String stadiumName) {
		this.stadiumName = stadiumName;
	}
	
	public Integer getLeagueID() {
		return leagueID;
	}
	
	public void setLeagueID(Integer leagueID) {
		this.leagueID = leagueID;
	}
	
	public String getTeamLocation() {
		return teamLocation;
	}

	
	public Double getTransferBudget() {
		return transferBudget;
	}
	
	public void setTransferBudget(Double transferBudget) {
		this.transferBudget = transferBudget;
		notifyObservers();
	}
	
	public Double getWageBudget() {
		return wageBudget;
	}
	
	public void setWageBudget(Double wageBudget) {
		this.wageBudget = wageBudget;
		notifyObservers();
	}
	
	public String getStadiumName() {
		return stadiumName;
	}
	
	@Override
	public String toString() {
		return String.format("Team ID: %d, Name=%s, Location=%s, Stadium=%s, TransferBudget=%.1f, WageBudget=%.1f",
				getId(),
				getTeamName(),
				getTeamLocation(),
				getStadiumName(),
				getTransferBudget(),
				getWageBudget());
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
			observer.update(this);
		}
	}
	
}