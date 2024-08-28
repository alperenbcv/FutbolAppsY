package FootballApp.entities;

import FootballApp.entities.attributes.GKAttributes;
import FootballApp.entities.attributes.MentalAttributes;
import FootballApp.entities.attributes.PhysicalAttributes;
import FootballApp.entities.attributes.TechnicalAttributes;
import FootballApp.entities.observerPatterns.Observable;
import FootballApp.entities.observerPatterns.Observer;
import FootballApp.enums.EPosition;
import FootballApp.models.DatabaseModels;

import javax.swing.text.Position;
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
	private MentalAttributes playerMentalAttributes;
	private PhysicalAttributes playerPhysicalAttributes;
	private GKAttributes gkAttributes;
	
	public Player() {
		super(++playerCounter);
	}
	
	public Player(String name, String surName, Integer age, String nationality,
	              TechnicalAttributes technicalAttributes, MentalAttributes mentalAttributes, PhysicalAttributes physicalAttributes,
	              Integer teamID, Double playerValue, Double playerWage, EPosition EplayersPosition) {
		super(++playerCounter,name,surName,age,nationality);
		this.currentTeamID=teamID;
		this.playerValue = playerValue;
		this.playerWage = playerWage;
		this.playersPosition = EplayersPosition;
		this.playerTechnicalAttributes = technicalAttributes;
		this.playerMentalAttributes = mentalAttributes;
		this.playerPhysicalAttributes = physicalAttributes;
		this.playerOverallRating=calculateOverallRating();
		DatabaseModels.playerDB.save(this);
	}
	
	public Player(String name, String surName, Integer age, String nationality,
	              GKAttributes gkAttributes,
	              Integer teamID, Double playerValue, Double playerWage) {
		super(++playerCounter, name, surName, age, nationality);
		this.currentTeamID = teamID;
		this.playerValue = playerValue;
		this.playerWage = playerWage;
		this.playersPosition = EPosition.GK;
		this.gkAttributes = gkAttributes;
		this.playerOverallRating = calculateOverallRating();
		DatabaseModels.playerDB.save(this);
	}
	
	
	private Integer calculateOverallRating() {
		if(playersPosition.equals(EPosition.ST)){
			// Forwards - emphasize on Finishing, Shot Power, and Dribbling
			this.playerOverallRating = (int) (
					0.4 * playerTechnicalAttributes.getFinishing() +
							0.3 * playerTechnicalAttributes.getShotPower() +
							0.2 * playerTechnicalAttributes.getDribbling() +
							0.1 * playerPhysicalAttributes.getSpeed()
			);
		}
		else if(playersPosition.equals(EPosition.CM)){
			// Midfielders - emphasize on Passing, Vision, and Dribbling
			this.playerOverallRating = (int) (
					0.35 * playerTechnicalAttributes.getPass() +
							0.2 * playerTechnicalAttributes.getDribbling() +
							0.15 * playerTechnicalAttributes.getTackle() +
							0.1 * playerMentalAttributes.getVision() +
							0.1 * playerPhysicalAttributes.getStamina() +
							0.1 * playerTechnicalAttributes.getFinishing()
			);
		}
		else if(playersPosition.equals(EPosition.CB)){
			// Defenders - emphasize on Tackling, Strength, and Heading
			this.playerOverallRating = (int) (
					0.4 * playerTechnicalAttributes.getTackle() +
							0.2 * playerTechnicalAttributes.getHeader() +
							0.2 * playerPhysicalAttributes.getStrength() +
							0.1 * playerPhysicalAttributes.getJumping() +
							0.1 * playerMentalAttributes.getDecisionMaking()
			);
		}
		else if(playersPosition.equals(EPosition.GK)){
			this.playerOverallRating = (int) (
					0.35 * gkAttributes.getPositioning() +
							0.3 * gkAttributes.getReflexes() +
							0.2 * gkAttributes.getDiving() +
							0.15 * gkAttributes.getOneOnOne()
			);
		}
		// Include other positions like LW, RW, LB, RB as needed
		else if(playersPosition.equals(EPosition.LW) || playersPosition.equals(EPosition.RW)){
			// Wingers - emphasize on Speed, Crossing, and Dribbling
			this.playerOverallRating = (int) (
					0.3 * playerTechnicalAttributes.getDribbling() +
							0.25 * playerTechnicalAttributes.getCrossing() +
							0.2 * playerPhysicalAttributes.getSpeed() +
							0.15 * playerTechnicalAttributes.getFinishing() +
							0.1 * playerMentalAttributes.getVision()
			);
		}
		else if(playersPosition.equals(EPosition.LB) || playersPosition.equals(EPosition.RB)){
			// Fullbacks - emphasize on Tackling, Speed, and Stamina
			this.playerOverallRating = (int) (
					0.3 * playerTechnicalAttributes.getTackle() +
							0.25 * playerPhysicalAttributes.getSpeed() +
							0.2 * playerPhysicalAttributes.getStamina() +
							0.15 * playerTechnicalAttributes.getCrossing() +
							0.1 * playerMentalAttributes.getDecisionMaking()
			);
		}
		
		return playerOverallRating;
	}
	
	public MentalAttributes getPlayerMentalAttributes() {
		return playerMentalAttributes;
	}
	
	public void setPlayerMentalAttributes(MentalAttributes playerMentalAttributes) {
		this.playerMentalAttributes = playerMentalAttributes;
	}
	
	public PhysicalAttributes getPlayerPhysicalAttributes() {
		return playerPhysicalAttributes;
	}
	
	public void setPlayerPhysicalAttributes(PhysicalAttributes playerPhysicalAttributes) {
		this.playerPhysicalAttributes = playerPhysicalAttributes;
	}
	
	public GKAttributes getGkAttributes() {
		return gkAttributes;
	}
	
	public void setGkAttributes(GKAttributes gkAttributes) {
		this.gkAttributes = gkAttributes;
	}
	
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
			observer.update(this);
		}
	}
}