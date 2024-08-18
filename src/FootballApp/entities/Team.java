package FootballApp.entities;

import java.util.ArrayList;
import java.util.List;

public class Team extends BaseEntity implements Subject{
	private List<Observer> observers = new ArrayList<>();
	
	private static Integer teamCounter=0;
	private int counter;
	private String teamName;
	private List<Integer> teamPlayerIDList;
	private String teamLocation;
	private Double transferBudget;
	private Double wageBudget;
	private String stadiumName;
	
	public Team() {
		super(++teamCounter);
	}
	
	public Team(String teamName, List<Integer> teamPlayerIDList, String teamLocation,
	            String stadiumName, Double transferBudget, Double wageBudget) {
		super(++teamCounter);
		this.teamName = teamName;
		this.teamPlayerIDList = teamPlayerIDList;
		this.teamLocation = teamLocation;
		this.stadiumName = stadiumName;
		this.transferBudget = transferBudget;
		this.wageBudget = wageBudget;
		this.counter = ++counter;
	}
	


	public String getTeamName() {
		return teamName;
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

	public List<Integer> getTeamPlayerIDList() {
		return teamPlayerIDList;
	}
	
	public void setTeamPlayerIDList(List<Integer> teamPlayerIDList) {
		this.teamPlayerIDList = teamPlayerIDList;
		notifyObservers();
	}
	
	public String getTeamLocation() {
		return teamLocation;
	}
	
	public String getStadiumName() {
		return stadiumName;
	}

	
	@Override
	public String toString() {
		return "Team "
				+ "ID: " + getId()
				+ ", Name=" + getTeamName()
				+ ", Location=" + getTeamLocation()
				+ ", Stadium=" + getStadiumName()
				+ ", TransferBudget=" + getTransferBudget()
				+ ", WageBudget=" + getWageBudget()
				+ ", TeamPlayersIDList=" + getTeamPlayerIDList();
		
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

//	public String toString(boolean string) {
//		if(string){
//			return this.getId()+" "+this.getTeamName();
//		}
//		else{
//			return this.getId()+" "+this.getTeamName()+" "+this.getCurrentLeagueID();
//		}
//	}
}