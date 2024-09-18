package fr.edmine.SkyWars.Teams;

import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Team {

    private final TeamColor color;
    private final Set<Player> players;

    public Team(TeamColor color) {
        this.color = color;
        this.players = new HashSet<>();
    }

    public TeamColor getColor() {
        return color;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public boolean isFull(int maxSize) {
        return players.size() >= maxSize;
    }
    public boolean isEmpty() {
        return players.isEmpty();
    }
}
