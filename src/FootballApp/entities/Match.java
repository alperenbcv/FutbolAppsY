package FootballApp.entities;

import FootballApp.enums.EMatchStatus;
import FootballApp.enums.EResult;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Match extends BaseGame{
	private static Integer matchCounter=0;
	
	private Integer homeTeamID;
	private Integer awayTeamID;
	private Integer homeScore;
	private Integer awayScore;
	private EResult result;
	private List<Integer> scorers;
	
	
	public Match() {
		super(++matchCounter);
	}
	
	public Match( Integer homeTeamID, Integer awayTeamID,
	             EMatchStatus matchStatus, LocalTime startTime, EResult result,
	             Integer homeScore, Integer awayScore, List<Integer> scorers) {
		
		super(++matchCounter);
		this.result = result;
		this.homeScore = homeScore;
		this.awayScore = awayScore;
		this.scorers = scorers;
	}
	
	public Integer getHomeScore() {
		return homeScore;
	}
	
	public void setHomeScore(Integer homeScore) {
		this.homeScore = homeScore;
	}
	
	public Integer getAwayScore() {
		return awayScore;
	}
	
	public void setAwayScore(Integer awayScore) {
		this.awayScore = awayScore;
	}
	
	public EResult getResult() {
		return result;
	}
	
	public void setResult(EResult result) {
		this.result = result;
	}
	
	public List<Integer> getScorers() {
		return scorers;
	}
	
	public void setScorers(List<Integer> scorers) {
		this.scorers = scorers;
	}
	
	@Override
	public String toString() {
		return "Match{" + "id=" + getId() + ", homeScore=" + getHomeScore() + ", awayScore=" + getAwayScore() + ", " +
				"result=" + getResult() + ", scorers=" + getScorers() + super.toString()+'}';
	}
}