package FootballApp.entities;

import FootballApp.entities.attributes.TechnicalAttributes;
import FootballApp.enums.EPosition;

import java.util.ArrayList;
import java.util.List;

public class Player extends Person implements Observable {
	private List<Observer> observers = new ArrayList<>();
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
		
			if(playersPosition.equals(EPosition.ST)){
				this.playerOverallRating = (int)
						((0.15)*playerTechnicalAttributes.getDribbling()+
						((0.4)*playerTechnicalAttributes.getFinishing())+
						((0.10)*playerTechnicalAttributes.getPass())+
						((0.05)*playerTechnicalAttributes.getTackle())+
						((0.3)*playerTechnicalAttributes.getShotPower())); }
			if(playersPosition.equals(EPosition.CM)){
				this.playerOverallRating = (int)
						((0.15)*playerTechnicalAttributes.getDribbling()+
						((0.15)*playerTechnicalAttributes.getFinishing())+
						((0.35)*playerTechnicalAttributes.getPass())+
						((0.25)*playerTechnicalAttributes.getTackle())+
						((0.1)*playerTechnicalAttributes.getShotPower())); }
			if(playersPosition.equals(EPosition.CB)){
				this.playerOverallRating = (int)
						((0.05)*playerTechnicalAttributes.getDribbling()+
						((0.05)*playerTechnicalAttributes.getFinishing())+
						((0.25)*playerTechnicalAttributes.getPass())+
						((0.60)*playerTechnicalAttributes.getTackle())+
						((0.05)*playerTechnicalAttributes.getShotPower())); }
			if(playersPosition.equals(EPosition.GK)){
				this.playerOverallRating = (int)
						(((1)*playerTechnicalAttributes.getDribbling() +
                        ((1)*playerTechnicalAttributes.getFinishing())   +
                        ((1)*playerTechnicalAttributes.getPass())+
                        ((1)*playerTechnicalAttributes.getTackle())+
                        ((1)*playerTechnicalAttributes.getShotPower()))/5);
			}
		return playerOverallRating;
	}
	
//	public String getCurrentTeamName(Integer currentTeamID) {
//		Optional<Team> team = db.findByID(currentTeamID);
//		return team.map(Team::getTeamName).orElse("Free Agent");
//	}
	
	
	public void setCurrentTeamID(Integer currentTeamID) {
		this.currentTeamID = currentTeamID;
		notifyObservers();
	}
	
	public TechnicalAttributes getPlayerTechnicalAttributes() {
		return playerTechnicalAttributes;
	}
	
	public void setPlayerTechnicalAttributes(TechnicalAttributes playerTechnicalAttributes) {
		this.playerTechnicalAttributes = playerTechnicalAttributes;
		notifyObservers();
	}
	
	public Integer getCurrentTeamID() {
		return currentTeamID;
	}

	public Integer getPlayerOverallRating() {
		return playerOverallRating;
	}
	
	public Double getPlayerValue() {
		return playerValue;
	}
	
	public void setPlayerValue(Double playerValue) {
		this.playerValue = playerValue;
		notifyObservers();
	}
	
	public Double getPlayerWage() {
		return playerWage;
	}
	
	public void setPlayerWage(Double playerWage) {
		this.playerWage = playerWage;
		notifyObservers();
	}
	
	public EPosition getPlayersPosition() {
		return playersPosition;
	}
	
	public void setPlayersPosition(EPosition playersPosition) {
		this.playersPosition = playersPosition;
		notifyObservers();
	}
	
	@Override
	public String toString() {
		return "Player"
				+ " ID: " + getId()
				+ ", Name=" + getName()
				+ ", Surname=" + getSurName()
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
			observer.update();
		}
	}
}