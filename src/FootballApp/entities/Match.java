package FootballApp.entities;

import FootballApp.enums.EMatchStatus;
import FootballApp.models.DatabaseModels;
import FootballApp.utility.DataIO;

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
    private Field field;
    private Ball ball;
    
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
    
    private void initializePlayerPositions() {
        // Example: Place players at starting positions
        List<Player> homeTeam = DatabaseModels.teamDB.findPlayersByTeamID(homeTeamId);
        for (int i = 0; i < homeTeam.size(); i++) {
            homeTeam.get(i).setPosition(new Position(10 + i * 10, 10 + i * 10));
        }
        
        List<Player> awayTeam = DatabaseModels.teamDB.findPlayersByTeamID(awayTeamId);
        
        for (int i = 0; i < awayTeam.size(); i++) {
            awayTeam.get(i).setPosition(new Position(field.getWidth() - 10 + i * 10, field.getLength()-10 + i * 10));
        }
        
    }
    
    public void setHomeTeamId(int homeTeamId) {
        this.homeTeamId = homeTeamId;
    }
    
    public void setAwayTeamId(int awayTeamId) {
        this.awayTeamId = awayTeamId;
    }
    
    public void setMatchDate(LocalDate matchDate) {
        this.matchDate = matchDate;
    }
    
    public Field getField() {
        return field;
    }
    
    public void setField(Field field) {
        this.field = field;
    }
    
    public Ball getBall() {
        return ball;
    }
    
    public void setBall(Ball ball) {
        this.ball = ball;
    }
    
    public Integer getHomeTeamScore() {
        return homeTeamScore;
    }
    
    public void setHomeTeamScore(Integer homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
        notifyObservers();
    }
    
    public Integer getAwayTeamScore() {
        return awayTeamScore;
    }
    
    public void setAwayTeamScore(Integer awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
        notifyObservers();
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
        notifyObservers();
    }
    
    public Integer getLeagueId() {
        return leagueId;
    }
    
    public void setLeagueId(Integer leagueId) {
        this.leagueId = leagueId;
        notifyObservers();
    }
    
    @Override
    public String toString() {
        return "Match= " + "Home Team: " + homeTeamId + " " + getHomeTeamScore() + " " + "Away Team: " + awayTeamId + " " + getAwayTeamScore();
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
            DataIO.getInstance().update(this);
        }
    }
}