package FootballApp.databases;

import FootballApp.entities.Fixture;
import FootballApp.entities.League;
import FootballApp.entities.Player;
import FootballApp.utility.DatabaseManager;

import java.util.List;
import java.util.Optional;

public class FixtureDB extends DatabaseManager<Fixture> {
	
	public Optional<Fixture> findByID(Integer fixtureID) {
		for (Fixture fixture : veriListesi) {
			if (fixture.getId() == fixtureID) {
				return Optional.of(fixture);
			}
		}
		return Optional.empty();
	}
	
	public List<Fixture> listAll() {
		if (veriListesi.isEmpty()) {
			return null;
		}
		else {
			return veriListesi;
		}
		
	}
}