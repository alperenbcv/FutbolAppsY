package FootballApp.entities;

import FootballApp.enums.EMatchStatus;
import FootballApp.utility.DataIO;

import java.time.LocalDate;
import java.util.*;

public class Fixture extends BaseGame {

	private static Integer fixtureCounter = 0;

	private Integer teamCount;
	private LocalDate seasonStartDate;
	private LocalDate seasonEndDate;
	private List<Match> matches;

	public Fixture(Integer teamCount) {
		super(++fixtureCounter);
		this.teamCount = teamCount;
		this.seasonStartDate = LocalDate.of(2024, 8, 23);
		this.seasonEndDate = LocalDate.of(2025, 6, 1);
		this.matches = new ArrayList<>();
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

	public List<Match> getMatches() {
		return matches;
	}

	public void addMatch(Match match) {
		this.matches.add(match);
	}

	@Override
	public String toString() {
		return "Fixture{" +
				"id=" + id +
				", teamCount=" + teamCount +
				", seasonStartDate=" + seasonStartDate +
				", seasonEndDate=" + seasonEndDate +
				'}';
	}

	public void generateFixtures(List<Integer> teams, int rounds) {
		LocalDate matchDate = this.seasonStartDate;

		for (int round = 1; round <= rounds; round++) {
			LocalDate[] matchDates = getWeekMatchDates(matchDate);
			boolean reverseHomeAway = (round % 2 == 0);
			for (int i = 0; i < teamCount / 2; i++) {
				int home = teams.get(i);
				int away = teams.get(teamCount - 1 - i);
				if (reverseHomeAway) {
					int temp = home;
					home = away;
					away = temp;
				}
				Match match = new Match(home, away, matchDates[i % 4], EMatchStatus.SCHEDULED);
				this.addMatch(match);
			}
			Collections.rotate(teams.subList(1, teamCount), 1);
			matchDate = matchDate.plusWeeks(1);
		}
	}

	private LocalDate[] getWeekMatchDates(LocalDate startDate) {
		return new LocalDate[]{startDate, // Friday
				startDate.plusDays(1), // Saturday
				startDate.plusDays(2), // Sunday
				startDate.plusDays(3) // Monday
		};
	}

	public static void printFixtureDetails(Fixture fixture) {
		System.out.println("Fixture ID: " + fixture.getId());
		System.out.println("Season Start Date: " + fixture.getSeasonStartDate());
		System.out.println("Season End Date: " + fixture.getSeasonEndDate());
		System.out.println("Team Count: " + fixture.getTeamCount());
		System.out.println();
		System.out.println("Matches:");

		Map<Integer, List<Match>> matchesByWeek = new LinkedHashMap<>();
		int matchWeek = 1;
		LocalDate currentWeekStartDate = fixture.getSeasonStartDate();

		for (Match match : fixture.getMatches()) {
			while (match.getMatchDate().isAfter(currentWeekStartDate.plusDays(6))) {
				matchWeek++;
				currentWeekStartDate = currentWeekStartDate.plusWeeks(1);
			}
			matchesByWeek.computeIfAbsent(matchWeek, k -> new ArrayList<>()).add(match);
		}

		for (Map.Entry<Integer, List<Match>> entry : matchesByWeek.entrySet()) {
			System.out.println("Week " + entry.getKey() + ":");
			for (Match match : entry.getValue()) {
				String homeTeamName = DataIO.teamDB.findByID(match.getHomeTeamId()).map(Team::getTeamName).orElse("Unknown");
				String awayTeamName = DataIO.teamDB.findByID(match.getAwayTeamId()).map(Team::getTeamName).orElse("Unknown");
				System.out.println("  " + match.getMatchDate() + ": " + homeTeamName + " vs " + awayTeamName);
			}
		}
	}

	public static void printFixtureDetailsOfATeam(Fixture fixture,String teamName) {
		System.out.println("Fixture ID: " + fixture.getId());
		System.out.println("Season Start Date: " + fixture.getSeasonStartDate());
		System.out.println("Season End Date: " + fixture.getSeasonEndDate());
		System.out.println("Team Count: " + fixture.getTeamCount());
		System.out.println();
		System.out.println("Matches:");

		Map<Integer, List<Match>> matchesByWeek = new LinkedHashMap<>();
		int matchWeek = 1;
		LocalDate currentWeekStartDate = fixture.getSeasonStartDate();

		for (Match match : fixture.getMatches()) {
			while (match.getMatchDate().isAfter(currentWeekStartDate.plusDays(6))) {
				matchWeek++;
				currentWeekStartDate = currentWeekStartDate.plusWeeks(1);
			}
			matchesByWeek.computeIfAbsent(matchWeek, k -> new ArrayList<>()).add(match);
		}

		for (Map.Entry<Integer, List<Match>> entry : matchesByWeek.entrySet()) {
			System.out.println("Week " + entry.getKey() + ":");
			for (Match match : entry.getValue()) {
				String homeTeamName = DataIO.teamDB.findByID(match.getHomeTeamId()).map(Team::getTeamName).orElse("Unknown");
				String awayTeamName = DataIO.teamDB.findByID(match.getAwayTeamId()).map(Team::getTeamName).orElse("Unknown");
				if(match.getHomeTeamId()==DataIO.teamDB.findByName(teamName).get().getId() || match.getAwayTeamId()==DataIO.teamDB.findByName(teamName).get().getId()){
					System.out.println("  " + match.getMatchDate() + ": " + homeTeamName + " vs " + awayTeamName);
					}
				}

			}
		}
	}


