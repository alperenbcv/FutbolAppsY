package FootballApp.entities;

import FootballApp.enums.ERegion;

import java.time.LocalDate;
import java.util.List;

public class League extends BaseEntity{
	private static Integer leagueCounter=0;
	
	private String leagueName;
	private Integer leagueStandingTableID;
	private List<Integer> leagueTeamIDList;
	private Integer leagueFixtureID;
	private ERegion regionList;
	private String season;
	private Integer division;
	private LocalDate seasonStartDate;
	private LocalDate seasonEndDate;

	public League() {
		super(++leagueCounter);
	}

	public League(String leagueName,Integer leagueStandingTable, List<Integer> leagueTeamIDList, Integer leagueFixtureID, String season, Integer division, ERegion regionList) {
		super(++leagueCounter);
		this.leagueName = leagueName;
		this.leagueStandingTableID = leagueStandingTable;
		this.leagueTeamIDList = leagueTeamIDList;
		this.leagueFixtureID = leagueFixtureID;
//		this.teamIDList = teamIDList;
		this.season = season;
		this.division = division;
		this.regionList = regionList;
		this.seasonStartDate = LocalDate.of(2024, 8, 23);
		this.seasonEndDate = LocalDate.of(2025, 6, 1);
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
	
	public static Integer getLeagueCounter() {
		return leagueCounter;
	}
	
	public static void setLeagueCounter(Integer leagueCounter) {
		League.leagueCounter = leagueCounter;
	}
	
	public String getLeagueName() {
		return leagueName;
	}
	
	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}
	
	public Integer getLeagueStandingTableID() {
		return leagueStandingTableID;
	}
	
	public void setLeagueStandingTableID(Integer leagueStandingTableID) {
		this.leagueStandingTableID = leagueStandingTableID;
	}
	
	public List<Integer> getLeagueTeamIDList() {
		return leagueTeamIDList;
	}
	
	public void setLeagueTeamIDList(List<Integer> leagueTeamIDList) {
		this.leagueTeamIDList = leagueTeamIDList;
	}
	
	public Integer getLeagueFixtureID() {
		return leagueFixtureID;
	}
	
	public void setLeagueFixtureID(Integer leagueFixtureID) {
		this.leagueFixtureID = leagueFixtureID;
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
	
	@Override
	public String toString() {
		return "League{" + "id=" + getId() + ", leagueName='" + getLeagueName() + '\'' + ", regionList=" + getRegionList() + ", division=" + division + ", season='" + season + '\'' + ", leagueTeamIDList=" + leagueTeamIDList + ", leagueStandingTableID=" + leagueStandingTableID + ", leagueFixtureID=" + leagueFixtureID + '}';
	}
}