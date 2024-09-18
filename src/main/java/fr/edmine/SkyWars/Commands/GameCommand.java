package fr.edmine.SkyWars.Commands;

import fr.edmine.SkyWars.Games.GameManager;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameCommand implements CommandExecutor {

    private final GameManager gameManager;

    public GameCommand(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Cette commande ne peut etre utilisée que par un joueur:");
            return true;
        }

        Player player = (Player) sender;
        if (args.length == 0) {
            player.sendMessage("Utilisez /startgame <solo | team2 | team4>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "solo":

                break;

            case "team2":

                break;
            case "team4":

                break;
            default:
                player.sendMessage("Mode non reconnu. Utilisez: solo, team2, team4.");
                break;
        }
        return true;
    }
}
