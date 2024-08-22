package FootballApp.databases;

import FootballApp.entities.League;
import FootballApp.entities.Manager;
import FootballApp.entities.Player;
import FootballApp.entities.Team;
import FootballApp.models.DatabaseModels;
import FootballApp.utility.DatabaseManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManagerDB extends DatabaseManager<Manager> {
	
	public Optional<Manager> findByID(Integer managerID) {
		for (Manager manager : veriListesi) {
			if (manager.getId()==managerID) {
				return Optional.of(manager);
			}
		}
		return Optional.empty();
	}
	
	public Optional<Manager> findByName(String name) {
		for (Manager manager : veriListesi) {
			if (manager.getName().equals(name)) {
				return Optional.of(manager);
			}
		}
		return Optional.empty();
	}
	
	public List<Manager> listAll() {
		return veriListesi;
	}
	
	public Optional<Manager> findByUsernameAndPassword(String username, String password) {
		for (Manager manager : veriListesi) {
            if (manager.getManagerUserName().equals(username) && manager.getManagerPassword().equals(password)) {
                return Optional.of(manager);
            }
        }
        return Optional.empty();
	}

	public List<Manager> findByLeagueID(Integer leagueID) {
		Optional<League> byID = DatabaseModels.leagueDB.findByID(leagueID);
		List<Manager> matchingManagers = new ArrayList<>();

		if (byID.isPresent()) {
			League league = byID.get();
			List<Integer> leagueTeamIDList = league.getLeagueTeamIDList();
			List<Manager> managers = listAll();

			for (Manager manager : managers) {
				Integer managerTeamID = manager.getCurrentTeamID();
				if (leagueTeamIDList.contains(managerTeamID)) {
					matchingManagers.add(manager);
				}
			}
		}

		return matchingManagers;
	}


}