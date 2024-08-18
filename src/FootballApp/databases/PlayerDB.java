package FootballApp.databases;


import FootballApp.entities.Player;
import FootballApp.entities.Team;
import FootballApp.utility.DataGenerator;
import FootballApp.utility.DataIO;
import FootballApp.utility.DatabaseManager;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class PlayerDB extends DatabaseManager<Player> {
	
	public Optional<Player> findByID(Integer playerID) {
		for (Player player : veriListesi) {
			if (player.getId()==playerID) {
				return Optional.of(player);
			}
		}
		return Optional.empty();
	}
	
	public Optional<Player> findByName(String name) {
		for (Player player : veriListesi) {
			if (player.getName().equals(name)) {
				return Optional.of(player);
			}
		}
		return Optional.empty();
	}

	public List<Player> listAll() {
		return veriListesi;
	}

	public List<Player> playerListByID(Integer teamID) {
		List<Player> playerList = new ArrayList<>();
        for (Player player : veriListesi) {
            if (player.getCurrentTeamID() == teamID) {
                playerList.add(player);
            }
        }
        return playerList;
	}
	
	
	public List<Player> findByTeamID(Integer teamID) {
		List<Player> foundPlayers = new ArrayList<>();
        for (Player player : veriListesi) {
            if (player.getCurrentTeamID() == teamID) {
                foundPlayers.add(player);
            }
        }
        return foundPlayers;
	}
	
	public List<Player> findByPlayerName(String playerName) {
		List<Player> foundPlayers = new ArrayList<>();
		for (Player player : veriListesi) {
			if (player.getName().toLowerCase().contains(playerName.toLowerCase())) {
				foundPlayers.add(player);
				
			}
		}
		return foundPlayers;
	}
	
	public List<Player> findByTeamName(String teamName) {
		List<Player> foundPlayers = new ArrayList<>();
		
		Optional<Team> teamOpt = DataIO.teamDB.findByName(teamName);
		
		if (teamOpt.isEmpty()) {
			System.out.println("Cannot Find the Team! \"" + teamName + "\" Please Enter its full name");
			return foundPlayers;  // Return an empty list if the team is not found
		}
		
		List<Integer> teamPlayerIDList = teamOpt.get().getTeamPlayerIDList();
		
		for (Integer playerID : teamPlayerIDList) {
			Optional<Player> byID = DataIO.playerDB.findByID(playerID);
			byID.ifPresent(foundPlayers::add);
		}
		
		return foundPlayers;
	}
	
	
}