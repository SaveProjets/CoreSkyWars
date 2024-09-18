package fr.edmine.SkyWars.Games;

import fr.edmine.SkyWars.Teams.TeamColor;
import fr.edmine.SkyWars.Teams.TeamManager;
import jdk.internal.org.jline.terminal.TerminalBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TeamGame {

    private final int teamSize;
    private GameState state;
    private TeamManager teamManager;

    public TeamGame(int teamSize) {
        this.teamSize = teamSize;
        this.teamManager = new TeamManager();
    }

    public void startGame() {
        state = GameState.IN_PROGRESS;
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (teamManager.getTeam(player) == null) {
                assignPlayerToTeam(player);
            }
        }

        System.out.println("Partie d'équipe de" + teamSize + " démarée !");
    }

    private void assignPlayerToTeam(Player player) {
        for (TeamColor color : TeamColor.values()) {
            if (!teamManager.getTeam(color).isFull(teamSize)) {
                teamManager.addPlayerToTeam(player, color);
                break;
            }
        }
    }

    public GameState getState() {
        return state;
    }

    public void endGame() {
        state = GameState.FINISHED;
        System.out.println("Partie d'équipe terminée !");
    }
}
