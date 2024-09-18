package fr.edmine.SkyWars.Listeners;

import fr.edmine.SkyWars.Teams.TeamColor;
import fr.edmine.SkyWars.Teams.TeamManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final TeamManager teamManager;

    public PlayerJoinListener(TeamManager teamManager) {
        this.teamManager = teamManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        for (TeamColor color : TeamColor.values()) {
        if (!teamManager.getTeam(color).isFull(4)) {
            teamManager.addPlayerToTeam(event.getPlayer(), color);
            event.getPlayer().sendMessage("Vous avez rejoint l'équipe " + color.name() + " !");
            break;
        }
       }
    }
}
