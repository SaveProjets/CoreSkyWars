package fr.edmine.SkyWars.Games;

import fr.edmine.SkyWars.Enums.GameMode;
import fr.edmine.SkyWars.Main;
import fr.edmine.SkyWars.Teams.Team;
import fr.edmine.SkyWars.Teams.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

public class GameManager {

    private GameState gameState;
    private final TeamManager teamManager;
    private List<Location> soloSpawns;
    private List<Location> team2Spawns;
    private List<Location> team4Spawns;

    public GameManager(TeamManager teamManager, List<Location> soloSpawns, List<Location> team2Spawns, List<Location> team4Spawns) {
        this.gameState = GameState.WAITING;
        this.teamManager = new TeamManager();
        this.soloSpawns = soloSpawns;
        this.team2Spawns = team2Spawns;
        this.team4Spawns = team4Spawns;
    }

    public void startGame(GameMode mode) {
        gameState = GameState.STARTING;
        Bukkit.getScheduler().runTaskLater(Main.getInstance(), this::beginMatch, 100L);
        switch (mode) {
            case SOLO:

                SoloGame soloGame = new SoloGame();
                soloGame.startGame();
                break;
            case TEAM_OF_TWO:
                TeamGame team2Game = new TeamGame(2);
                team2Game.startGame();
                break;

            case TEAM_OF_FOUR:
                TeamGame team4Game = new TeamGame(4);
                team4Game.startGame();
                break;
        }
    }

    private void beginMatch() {
        gameState = GameState.IN_PROGRESS;

        List<Team> teams = teamManager.getActiveTeams();
        for (int i = 0; i < teams.size(); i++) {
            Team team = teams.get(i);

            for (Player player : team.getPlayers()) {
                player.sendMessage("La partie commence ! Bonne Chance !");
            }
        }

        checkForWin();
    }

    public GameState getCurrentGameState() {
        return gameState;
    }

    public void handlePlayerElimination(Player player) {
        Team playerTeam = teamManager.getTeam(player);

        if (playerTeam == null) {
            playerTeam.removePlayer(player);
            Bukkit.broadcastMessage(player.getName() + " a été éliminé !");
        }

        checkForWin();
    }

    public void handlePlayerQuit(Player player) {
        handlePlayerElimination(player);
    }

    private void checkForWin() {
        Bukkit.getScheduler().runTaskTimer(Main.getInstance(), () -> {
            List<Team> activeTeams = teamManager.getActiveTeams();
            if (activeTeams.size() == 1) {
                endGame(activeTeams.get(0));
            }
        }, 20L, 100L);
    }

    public void endGame(Team winningTeam) {
        gameState = GameState.FINISHED;
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (winningTeam.getPlayers().contains(player)) {
                player.sendMessage("Félicitations ! Votre équipe a gagné !");
            } else {
                player.sendMessage("Partie terminée. L'équipe " + winningTeam.getColor() + " a gagné.");
            }
        }
        resetGame();
    }

    public void resetGame() {
        gameState = GameState.WAITING;
        teamManager.clearTeams();
    }
}
