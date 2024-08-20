package FootballApp.entities;

import FootballApp.enums.EMatchStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class Fixture extends BaseGame {
	
	private static Integer fixtureCounter = 0;
	
	private Integer teamCount;
	private LocalDate seasonStartDate;
	private LocalDate seasonEndDate;
	
	
	public Fixture(Integer teamCount) {
		super(++fixtureCounter);
		this.teamCount = teamCount;
		this.seasonStartDate = LocalDate.of(2024,8,23);
		this.seasonEndDate = LocalDate.of(2025,6,1);
	}
	
	
	
	public Integer getTeamCount() {
		return teamCount;
	}
	
	public void setTeamCount(Integer teamCount) {
		this.teamCount = teamCount;
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
	
}