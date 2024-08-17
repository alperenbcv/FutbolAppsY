package FootballApp.entities;

import FootballApp.databases.TeamDB;
import FootballApp.entities.attributes.TechnicalAttributes;
import FootballApp.enums.EPosition;
import FootballApp.utility.DatabaseManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Player extends Person {
	private static Integer playerCounter=0;
	
	private Integer playerOverallRating;
	private Integer currentTeamID;
	private Double playerValue;
	private Double playerWage;
	private EPosition playersPosition;
	private TechnicalAttributes playerTechnicalAttributes;
	
	public Player() {
		super(++playerCounter);
	}
	
	public Player(String name, String surName, Integer age, String nationality, TechnicalAttributes technicalAttributes,
	              Integer teamID, Double playerValue, Double playerWage, EPosition EplayersPosition) {
		super(++playerCounter,name,surName,age,nationality);
		this.currentTeamID=teamID;
		this.playerValue = playerValue;
		this.playerWage = playerWage;
		this.playersPosition = EplayersPosition;
		this.playerTechnicalAttributes = technicalAttributes;
		this.playerOverallRating=calculateOverallRating();
	}
	
	private Integer calculateOverallRating() {
		this.playerOverallRating = (playerTechnicalAttributes.getDribbling()+ playerTechnicalAttributes.getFinishing()
				+playerTechnicalAttributes.getPass()+playerTechnicalAttributes.getTackle()+playerTechnicalAttributes.getShotPower())/5;
		return playerOverallRating;
	}
	
//	public String getCurrentTeamName(Integer currentTeamID) {
//		Optional<Team> team = db.findByID(currentTeamID);
//		return team.map(Team::getTeamName).orElse("Free Agent");
//	}
	
	
	public void setCurrentTeamID(Integer currentTeamID) {
		this.currentTeamID = currentTeamID;
	}
	
	public TechnicalAttributes getPlayerTechnicalAttributes() {
		return playerTechnicalAttributes;
	}
	
	public void setPlayerTechnicalAttributes(TechnicalAttributes playerTechnicalAttributes) {
		this.playerTechnicalAttributes = playerTechnicalAttributes;
	}
	
	public Integer getCurrentTeamID() {
		return currentTeamID;
	}

	public Integer getPlayerOverallRating() {
		return playerOverallRating;
	}
	
	public void setPlayerOverallRating(Integer playerOverallRating) {
		this.playerOverallRating = playerOverallRating;
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
	
	@Override
	public String toString() {
		return "Player"
				+ " ID: " + getId()
				+ ", Name=" + getName()
				+ ", SurName=" + getSurName()
				+ ", Age=" + getAge()
				+ ", Nationality=" + getNationality()
				+ ", CurrentTeamID=" + currentTeamID
//				+ ", CurrentTeamName=" + getCurrentTeamName(currentTeamID)
				+ ", Position=" + getPlayersPosition()
				+ ", OverallRating=" + getPlayerOverallRating()
				+ ", Value=" + getPlayerValue()
				+ ", Wage=" + getPlayerWage()
				+ ", " + playerTechnicalAttributes;
	}
}