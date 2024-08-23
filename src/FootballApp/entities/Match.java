package FootballApp.entities;

import FootballApp.enums.EMatchStatus;
import FootballApp.models.DatabaseModels;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Match extends BaseEntity implements Observable {
    private List<Observer> observers = new ArrayList<>();
    private static Integer matchCounter = 0;
    
    private int homeTeamId;
    private int awayTeamId;
    private LocalDate matchDate;
    private EMatchStatus status;
    private Integer leagueId;
    private Integer homeTeamScore;
    private Integer awayTeamScore;
    
    public Match() {
        super(++matchCounter);
    }
    
    public Match(int homeTeamId, int awayTeamId, LocalDate matchDate, EMatchStatus status, Integer leagueId) {
        super(++matchCounter);
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
        this.matchDate = matchDate;
        this.status = status;
        this.leagueId = leagueId;
        this.homeTeamScore=0;
        this.awayTeamScore=0;
        DatabaseModels.matchDB.save(this);
    }
    
    public Integer getHomeTeamScore() {
        return homeTeamScore;
    }
    
    public void setHomeTeamScore(Integer homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }
    
    public Integer getAwayTeamScore() {
        return awayTeamScore;
    }
    
    public void setAwayTeamScore(Integer awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }
    
    public int getHomeTeamId() {
        return homeTeamId;
    }

    public int getAwayTeamId() {
        return awayTeamId;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public EMatchStatus getStatus() {
        return status;
    }

    public void setStatus(EMatchStatus status) {
        this.status = status;
    }
    
    public Integer getLeagueId() {
        return leagueId;
    }
    
    public void setLeagueId(Integer leagueId) {
        this.leagueId = leagueId;
    }
    
    @Override
    public String toString() {
        return "Match{" +
                "homeTeamId=" + homeTeamId +
                ", awayTeamId=" + awayTeamId +
                ", matchDate=" + matchDate +
                ", status=" + status +
                '}';
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