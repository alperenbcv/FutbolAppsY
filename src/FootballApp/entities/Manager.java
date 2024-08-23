package FootballApp.entities;

import FootballApp.models.DatabaseModels;

import java.util.ArrayList;
import java.util.List;

public class Manager extends Person implements Observable {
	private List<Observer> observers = new ArrayList<>();
	private static Integer managerCounter=0;
	
	private String managerUserName;
	private String managerPassword;
	private Integer currentTeamID;
	
	public Manager() {
		super(++managerCounter);
	}
	
	public Manager(Integer currentTeamID, String name, String surName, Integer age, String nationality, String managerUserName, String managerPassword) {
		super(++managerCounter, name, surName, age, nationality);
		this.managerUserName = managerUserName;
		this.managerPassword = managerPassword;
		this.currentTeamID= currentTeamID;
		DatabaseModels.managerDB.save(this);
	}
	
	public String getManagerUserName() {
		return managerUserName;
	}
	
	public void setManagerUserName(String managerUserName) {
		this.managerUserName = managerUserName;
		notifyObservers();
	}
	
	public String getManagerPassword() {
		return managerPassword;
	}
	
	public void setManagerPassword(String managerPassword) {
		this.managerPassword = managerPassword;
		notifyObservers();
	}
	
	public Integer getCurrentTeamID() {
		return currentTeamID;
	}
	
	public void setCurrentTeamID(Integer currentTeamID) {
		this.currentTeamID = currentTeamID;
		notifyObservers();
	}
	
	@Override
	public String toString() {
		return "Manager{" +"CurrenTeamId"+ getCurrentTeamID() + "id=" + getId() + ", name='" + getName() + '\'' + ", age=" + getAge() + ", nationality='" + getNationality() + '\'' + ", managerUserName='" + getManagerUserName() + '\'' + ", managerPassword='" + getManagerPassword() + '\'' + ", currentTeamID=" + getCurrentTeamID() + '}';
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