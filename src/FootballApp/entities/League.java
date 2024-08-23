package FootballApp.entities;

import FootballApp.enums.ERegion;
import FootballApp.models.DatabaseModels;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class League extends BaseEntity implements Observable{
	private List<Observer> observers = new ArrayList<>();
	private static Integer leagueCounter=0;
	
	private String leagueName;
	private Integer leagueStandingTableID;
	private ERegion region;
	private String season;
	private Integer division;
	private LocalDate seasonStartDate;
	private LocalDate seasonEndDate;
	private List<Integer> leagueTeamIDList;

	public League() {
		super(++leagueCounter);
	}

	public League(String leagueName,Integer leagueStandingTable, String season, Integer division, ERegion region) {
		super(++leagueCounter);
		this.leagueName = leagueName;
		this.leagueStandingTableID = leagueStandingTable;
		this.season = season;
		this.division = division;
		this.region = region;
		this.leagueTeamIDList = new ArrayList<>();
		this.seasonStartDate = LocalDate.of(2024, 8, 23);
		this.seasonEndDate = LocalDate.of(2025, 6, 1);
		DatabaseModels.leagueDB.save(this);
	}
	
	public List<Integer> getLeagueTeamIDList() {
		return leagueTeamIDList;
	}
	
	public void setLeagueTeamIDList(Integer teamID) {
		this.leagueTeamIDList.add(teamID);
	}
	
	public LocalDate getSeasonStartDate() {
		return seasonStartDate;
	}
	
	public void setSeasonStartDate(LocalDate seasonStartDate) {
		this.seasonStartDate = seasonStartDate;
		notifyObservers();
	}
	
	public LocalDate getSeasonEndDate() {
		return seasonEndDate;
	}
	
	public void setSeasonEndDate(LocalDate seasonEndDate) {
		this.seasonEndDate = seasonEndDate;
		notifyObservers();
	}
	
	public String getLeagueName() {
		return leagueName;
	}
	
	public Integer getLeagueStandingTableID() {
		return leagueStandingTableID;
	}
	
	public void setLeagueStandingTableID(Integer leagueStandingTableID) {
		this.leagueStandingTableID = leagueStandingTableID;
		notifyObservers();
	}
	
	public ERegion getRegion() {
		return region;
	}
	
	
	public String getSeason() {
		return season;
	}
	
	public void setSeason(String season) {
		this.season = season;
		notifyObservers();
	}
	
	public Integer getDivision() {
		return division;
	}

	@Override
	public String toString() {
		return "League{" + "id=" + getId() + ", leagueName='" + getLeagueName() + '\'' + ", regionList=" + getRegion() + ", division=" + division + ", season='" + season + '\'' + ", leagueTeamIDList=" + leagueTeamIDList + ", leagueStandingTableID=" + leagueStandingTableID+'}';
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