package FootballApp.models;

import FootballApp.entities.Player;
import FootballApp.entities.Team;
import FootballApp.entities.attributes.TechnicalAttributes;
import FootballApp.enums.EPosition;

public class PlayerModel {
	
	private Integer playerID;
	private String playerName;
	private String playerSurname;
	private Integer playerAge;
	private String playerNationality;
	private Integer playerOverallRating;
	private Integer currentTeamID;
	private Double playerValue;
	private Double playerWage;
	private EPosition playersPosition;
	private TechnicalAttributes playerTechnicalAttributes;
	private Team playersTeam;
	
	public PlayerModel(DatabaseModels databaseModel, Player player) {
		this.playerID = player.getId();
        this.playerName = player.getName();
        this.playerSurname = player.getSurName();
        this.playerAge = player.getAge();
        this.playerNationality = player.getNationality();
        this.playerOverallRating = player.getPlayerOverallRating();
        this.currentTeamID = player.getCurrentTeamID();
        this.playerValue = player.getPlayerValue();
        this.playerWage = player.getPlayerWage();
        this.playersPosition = player.getPlayersPosition();
        this.playerTechnicalAttributes = player.getPlayerTechnicalAttributes();
        this.playersTeam = databaseModel.teamDB.findByID(player.getCurrentTeamID()).get();
	}
	
	public Integer getPlayerID() {
		return playerID;
	}
	
	public void displayPlayerInfo() {
		System.out.println("Player Name    : " + this.playerName + " " + this.playerSurname);
		System.out.println("Age            : " + this.playerAge);
		System.out.println("Nationality    : " + this.playerNationality);
		System.out.println("Position       : " + this.playersPosition);
		System.out.println("Market Value   : " + this.playerValue);
		System.out.println("Wage           : " + this.playerWage);
		System.out.println("Overall Rating : " + this.playerOverallRating);
		System.out.println("Technical Attributes : " + this.playerTechnicalAttributes);
		if (this.playersTeam!=null) {
			System.out.println("Club           : " + this.playersTeam.getTeamName());
		} else {
			System.out.println("Club           : Free Agent");
		}
		System.out.println("--------------------------------------------------");
	}
	
	public void setPlayerID(Integer playerID) {
		this.playerID = playerID;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public String getPlayerSurname() {
		return playerSurname;
	}
	
	public void setPlayerSurname(String playerSurname) {
		this.playerSurname = playerSurname;
	}
	
	public Integer getPlayerAge() {
		return playerAge;
	}
	
	public void setPlayerAge(Integer playerAge) {
		this.playerAge = playerAge;
	}
	
	public String getPlayerNationality() {
		return playerNationality;
	}
	
	public void setPlayerNationality(String playerNationality) {
		this.playerNationality = playerNationality;
	}
	
	public Integer getPlayerOverallRating() {
		return playerOverallRating;
	}
	
	public void setPlayerOverallRating(Integer playerOverallRating) {
		this.playerOverallRating = playerOverallRating;
	}
	
	public Integer getCurrentTeamID() {
		return currentTeamID;
	}
	
	public void setCurrentTeamID(Integer currentTeamID) {
		this.currentTeamID = currentTeamID;
	}
	
	public Double getPlayerValue() {
		return playerValue;
	}
	
	public void setPlayerValue(Double playerValue) {
		this.playerValue = playerValue;
	}
	
	public Double getPlayerWage() {
		return playerWage;
	}
	
	public void setPlayerWage(Double playerWage) {
		this.playerWage = playerWage;
	}
	
	public EPosition getPlayersPosition() {
		return playersPosition;
	}
	
	public void setPlayersPosition(EPosition playersPosition) {
		this.playersPosition = playersPosition;
	}
	
	public TechnicalAttributes getPlayerTechnicalAttributes() {
		return playerTechnicalAttributes;
	}
	
	public void setPlayerTechnicalAttributes(TechnicalAttributes playerTechnicalAttributes) {
		this.playerTechnicalAttributes = playerTechnicalAttributes;
	}
	
	public Team getPlayersTeam() {
		return playersTeam;
	}
	
	public void setPlayersTeam(Team playersTeam) {
		this.playersTeam = playersTeam;
	}
}