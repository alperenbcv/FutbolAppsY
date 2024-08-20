package FootballApp.entities;

import FootballApp.enums.EMatchStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public class BaseGame extends BaseEntity {
	
	
	
	private LocalDate gameDate;
	private EMatchStatus matchStatus;
	
	public BaseGame(Integer id) {
		super(id);
	}
	
	public BaseGame(Integer id, LocalDate gameDate, EMatchStatus matchStatus) {
		super(id);
		this.gameDate = gameDate;
		this.matchStatus = matchStatus;
	}
	
	
}