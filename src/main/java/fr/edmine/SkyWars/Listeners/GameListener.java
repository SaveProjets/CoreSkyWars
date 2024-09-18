package fr.edmine.SkyWars.Listeners;

import fr.edmine.SkyWars.Games.GameManager;
import fr.edmine.SkyWars.Games.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class GameListener implements Listener {


    private final GameManager gameManager;

    public GameListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (gameManager.getCurrentGameState() == GameState.IN_PROGRESS) {
            event.getEntity().sendMessage("Vous etes mort !");

            gameManager.handlePlayerElimination(event.getEntity());
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (gameManager.getCurrentGameState() == GameState.IN_PROGRESS) {
            gameManager.handlePlayerQuit(event.getPlayer());
        }
    }
}
