package FootballApp.entities;

import FootballApp.models.DatabaseModels;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoPeriod;
import java.time.chrono.Chronology;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;

public class GameDate extends BaseEntity implements Temporal, TemporalAdjuster, ChronoLocalDate {
	private static Integer dateCounter=0;
	private LocalDate date;
	
	
	public GameDate(LocalDate date) {
		super(++dateCounter);
		this.date = date;
		DatabaseModels.dateDB.save(this);
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	@Override
	public boolean isSupported(TemporalUnit unit) {
		return false;
	}
	
	@Override
	public long until(Temporal endExclusive, TemporalUnit unit) {
		return 0;
	}
	
	@Override
	public ChronoPeriod until(ChronoLocalDate endDateExclusive) {
		return null;
	}
	
	@Override
	public Chronology getChronology() {
		return null;
	}
	
	@Override
	public int lengthOfMonth() {
		return 0;
	}
	
	@Override
	public boolean isSupported(TemporalField field) {
		return false;
	}
	
	@Override
	public long getLong(TemporalField field) {
		return 0;
	}
	
	@Override
	public Temporal adjustInto(Temporal temporal) {
		return null;
	}
	
	public GameDate plusDays(int i) {
		getDate().plusDays(i);
		return this;
	}
	
	public GameDate minusDays(int i) {
		getDate().minusDays(i);
		return this;
	}
}