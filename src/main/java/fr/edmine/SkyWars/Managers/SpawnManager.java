package fr.edmine.SkyWars.Managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SpawnManager {

    private List<Location> soloSpawns;
    private List<Location> team2Spawns;
    private List<Location> team4Spawns;

    public SpawnManager(FileConfiguration config) {
        this.soloSpawns = loadSpawnsFromConfig(config, "spawns.solo");
        this.team2Spawns = loadSpawnsFromConfig(config, "spawns.teams2");
        this.team4Spawns = loadSpawnsFromConfig(config, "spawns.teams4");
    }

    private List<Location> loadSpawnsFromConfig(FileConfiguration config, String path) {
        List<Location> spawnLocations = new ArrayList<>();
        List<Map<?, ?>> spawns = config.getMapList(path);

        for (Map<?, ?> spawn : spawns) {
            String world = (String) spawn.get("world");
            double x = (double) spawn.get("x");
            double y = (double) spawn.get("y");
            double z = (double) spawn.get("z");
            Location location = new Location(Bukkit.getWorld(world), x, y, z);

            spawnLocations.add(location);
        }

        return spawnLocations;
    }

    public List<Location> getSoloSpawns() {
        return soloSpawns;
    }

    public List<Location> getTeam2Spawns() {
        return team2Spawns;
    }

    public List<Location> getTeam4Spawns() {
        return team4Spawns;
    }
}
