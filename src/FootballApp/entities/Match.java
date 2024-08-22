package FootballApp.entities;

import FootballApp.enums.EMatchStatus;
import FootballApp.models.DatabaseModels;

import java.time.LocalDate;

public class Match extends BaseEntity {
    private static Integer matchCounter = 0;
    
    private int homeTeamId;
    private int awayTeamId;
    private LocalDate matchDate;
    private EMatchStatus status;
    private Integer leagueId;
    
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
        DatabaseModels.matchDB.save(this);
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
}