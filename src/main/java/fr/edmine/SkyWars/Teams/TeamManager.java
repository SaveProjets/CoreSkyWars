package fr.edmine.SkyWars.Teams;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamManager {
    private Map<TeamColor, Team> teams;


    public TeamManager() {
        teams = new HashMap<>();
        teams.put(TeamColor.RED, new Team(TeamColor.RED));
        teams.put(TeamColor.GREEN, new Team(TeamColor.GREEN));
        teams.put(TeamColor.YELLOW, new Team(TeamColor.YELLOW));
        teams.put(TeamColor.PURPLE, new Team(TeamColor.PURPLE));
    }

    public void addPlayerToTeam(Player player, TeamColor color) {
        Team team = teams.get(color);
        team.addPlayer(player);
    }

    public Team getTeam(Player player) {
        for (Team team : teams.values()) {
            if (team.getPlayers().contains(player)) {
                return team;
            }
        }
        return null;
    }

    public List<Team> getTeams() {
        return new ArrayList<>(teams.values());
    }

    public List<Team> getActiveTeams() {
        List<Team> activeTeams = new ArrayList<>();
        for (Team team : teams.values()) {
            if (!team.isEmpty()) {
                activeTeams.add(team);
            }
        }
        return activeTeams;
    }

    public void clearTeams() {
    }
}
