package fr.edmine.SkyWars;

import fr.edmine.SkyWars.Commands.GameCommand;
import fr.edmine.SkyWars.Games.GameManager;
import fr.edmine.SkyWars.Managers.SpawnManager;
import fr.edmine.SkyWars.Teams.TeamManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;
    private GameManager gameManager;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        TeamManager teamManager = new TeamManager();

        SpawnManager spawnManager = new SpawnManager(getConfig());

        gameManager = new GameManager(teamManager, spawnManager.getSoloSpawns(), spawnManager.getTeam2Spawns(), spawnManager.getTeam4Spawns());
        getLogger().info("Le plugin vient de s'allumer !");

        //Les Commandes
        getCommand("startgame").setExecutor(new GameCommand(gameManager));
    }

    @Override
    public void onDisable() {
        getLogger().info("Le plugin vient de s'eteindre !");
    }

    public static Main getInstance() {
        return instance;
    }
}
