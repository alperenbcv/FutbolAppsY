package FootballApp.entities;

import FootballApp.enums.EMatchStatus;

import java.time.LocalDate;

public class Match {

    private int homeTeamId;
    private int awayTeamId;
    private LocalDate matchDate;
    private EMatchStatus status;

    public Match(int homeTeamId, int awayTeamId, LocalDate matchDate, EMatchStatus status) {
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
        this.matchDate = matchDate;
        this.status = status;
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
